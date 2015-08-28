package hu.neuron.ier.business.bugreport.impl;

import hu.neuron.ier.business.bugreport.BugReportServiceRemote;
import hu.neuron.ier.business.converter.BugReportConverter;
import hu.neuron.ier.business.vo.BugReportVO;
import hu.neuron.ier.core.dao.BugMessageDao;
import hu.neuron.ier.core.dao.BugReportDao;
import hu.neuron.ier.core.dao.ClientDao;
import hu.neuron.ier.core.entity.BugReport;
import hu.neuron.ier.core.entity.Client;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.Interceptors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.ejb.interceptor.SpringBeanAutowiringInterceptor;

@Stateless(mappedName = "BugReportService", name = "BugReportService")
@Remote(BugReportServiceRemote.class)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@Interceptors(SpringBeanAutowiringInterceptor.class)
public class BugReportServiceImpl implements BugReportServiceRemote, Serializable {

	private static final long serialVersionUID = -8755685689233378047L;

	@Autowired
	BugReportDao bugReportDao;

	@Autowired
	BugMessageDao bugMessageDao;

	@Autowired
	ClientDao clientDao;

	@EJB
	BugReportConverter bugReportConverter;

	@Override
	public BugReportVO saveBugReport(BugReportVO bugReport) {
		BugReport it = bugReportDao.save(bugReportConverter.toEntity(bugReport));
		return bugReportConverter.toVO(it);
	}

	


	@Override
	public void removeBugReport(BugReportVO bugReport) {
		bugReportDao.delete(bugReportConverter.toEntity(bugReport));
	}

	@Override
	public BugReportVO findByReportId(String reportId) {
		return bugReportConverter.toVO(bugReportDao.findByReportId(reportId));
	}

	@Override
	public List<BugReportVO> findByClientId(String clientId) {
		return bugReportConverter.toVO(bugReportDao.findByClientId(clientId));
	}

	@Override
	public List<BugReportVO> getBugReportList(int page, int size, String sortField, int sortOrder, String filter, String filterColumnName) {

		boolean sortedByClientUserName = false;
//		boolean sortedByLastUpdate = false;

		if (sortField.equals("clientUserName")) {
			sortedByClientUserName = true;
			sortField = "clientId";
		}

		Direction dir = sortOrder == 1 ? Sort.Direction.ASC : Sort.Direction.DESC;
		PageRequest pageRequest = new PageRequest(page, size, new Sort(new org.springframework.data.domain.Sort.Order(dir, sortField)));
		List<BugReportVO> ret = new ArrayList<BugReportVO>(size);
		Page<BugReport> entities;

		if (filter.length() != 0 && filterColumnName.equals("clientUserName")) {
			entities = bugReportDao.findByClientIdIn(getClientIdsByUserName(filter), pageRequest);
		} else if (filter.length() != 0 && filterColumnName.equals("status")) {
			entities = bugReportDao.findByStatusStartsWith(filter, pageRequest);
		} else if (filter.length() != 0 && filterColumnName.equals("subject")) {
			entities = bugReportDao.findBySubjectStartsWith(filter, pageRequest);
		} else {
			entities = bugReportDao.findAll(pageRequest);
		}

		if (entities != null && entities.getSize() > 0) {
			List<BugReport> contents = entities.getContent();
			for (BugReport m : contents) {

				BugReportVO itVO = bugReportConverter.toVO(m);
				itVO.setClientUserName(clientDao.findByClientId(itVO.getClientId()).getUserName());
				ret.add(itVO);
			}
		}

		if (sortedByClientUserName) {
			sortByClientUserName(ret, sortOrder);
		}

		return ret;
	}

	@Override
	public int getBugReportCount() {
		return (int) bugReportDao.count();
	}

	private List<String> getClientIdsByUserName(String userName) {
		List<Client> clients = clientDao.findByuserNameStartsWith(userName);
		List<String> clientIds = new ArrayList<>();
		for (Client client : clients) {
			clientIds.add(client.getClientId());
		}
		
		if(clientIds.isEmpty()) {
			clientIds.add("");
		}
		
		return clientIds;
	}

	private List<BugReportVO> sortByClientUserName(List<BugReportVO> bugReports, int sortOrder) {
		if (sortOrder == 1) {
			Collections.sort(bugReports, new Comparator<BugReportVO>() {
				@Override
				public int compare(BugReportVO o1, BugReportVO o2) {
					return o1.getClientUserName().compareTo(o2.getClientUserName());
				}
			});

		} else {
			Collections.sort(bugReports, new Comparator<BugReportVO>() {
				@Override
				public int compare(BugReportVO o1, BugReportVO o2) {
					return -(o1.getClientUserName().compareTo(o2.getClientUserName()));
				}
			});
		}
		return bugReports;
	}

	@Override
	public long countOngoingBugReport() {
		Long count = bugReportDao.countByStatus("Ongoing");
		return count == null ? 0 : count;
	}
}
