package hu.neuron.ier.business.bugreport.impl;

import hu.neuron.ier.business.bugreport.BugMessageServiceRemote;
import hu.neuron.ier.business.converter.BugMessageConverter;
import hu.neuron.ier.business.vo.BugMessageVO;
import hu.neuron.ier.core.dao.BugMessageDao;
import hu.neuron.ier.core.entity.BugMessage;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.Interceptors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ejb.interceptor.SpringBeanAutowiringInterceptor;

@Stateless(mappedName = "BugMessageService", name = "BugMessageService")
@Remote(BugMessageServiceRemote.class)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@Interceptors(SpringBeanAutowiringInterceptor.class)
public class BugMessageServiceImpl implements BugMessageServiceRemote, Serializable{

	private static final long serialVersionUID = 1592904839407646632L;
	
	@Autowired
	BugMessageDao bugMessageDao;

	@EJB
	BugMessageConverter bugMessageConverter;

	@Override
	public BugMessageVO saveBugMessage(BugMessageVO bugMessage) {
		BugMessage im = bugMessageDao.save(bugMessageConverter.toEntity(bugMessage));
		return bugMessageConverter.toVO(im);
	}

	@Override
	public void removeBugMessage(BugMessageVO bugMessage) {
		bugMessageDao.delete(bugMessageConverter.toEntity(bugMessage));
	}

	@Override
	public List<BugMessageVO> findByReportId(String reportId) {
		return bugMessageConverter.toVO(bugMessageDao.findByReportId(reportId));
	}

	@Override
	public BugMessageVO findByBugMessageId(String bugMessageId) {
		return bugMessageConverter.toVO(bugMessageDao.findByMessageId(bugMessageId));
	}
}