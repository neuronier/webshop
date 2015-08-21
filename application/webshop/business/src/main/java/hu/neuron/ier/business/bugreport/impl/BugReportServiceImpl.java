package hu.neuron.ier.business.bugreport.impl;

import hu.neuron.ier.business.bugreport.BugReportServiceRemote;
import hu.neuron.ier.business.converter.BugReportConverter;
import hu.neuron.ier.business.converter.ClientConverter;
import hu.neuron.ier.business.converter.ProductTypeConverter;
import hu.neuron.ier.business.vo.BugReportVO;
import hu.neuron.ier.business.vo.ClientVO;
import hu.neuron.ier.business.vo.ProductTypeVO;
import hu.neuron.ier.core.dao.BugReportDao;
import hu.neuron.ier.core.dao.ClientDao;
import hu.neuron.ier.core.dao.ProductTypeDao;

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

@Stateless(mappedName = "BugReportService", name = "BugReportService")
@Remote(BugReportServiceRemote.class)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@Interceptors(SpringBeanAutowiringInterceptor.class)
public class BugReportServiceImpl implements BugReportServiceRemote, Serializable {

	private static final long serialVersionUID = -8755685689233378047L;

	@Autowired
	BugReportDao bugReportDao;
	@Autowired
	ClientDao clientDao;
	@Autowired
	ProductTypeDao productTypeDao;

	@EJB
	BugReportConverter bugReportConverter;
	@EJB
	ClientConverter clientConverter;
	@EJB
	ProductTypeConverter productTypeConverter;

	@Override
	public BugReportVO createBugReport(BugReportVO bugReportVO) throws Exception {
		BugReportVO vo = bugReportConverter.toVO(bugReportDao.save(bugReportConverter
				.toEntity(bugReportVO)));
		return vo;
	}

	@Override
	public void deleteBugReport(Long id) throws Exception {
		bugReportDao.delete(id);

	}

	@Override
	public BugReportVO updateProblem(Long id, String problem) throws Exception {
		BugReportVO bugReportVO = bugReportConverter.toVO(bugReportDao.findOne(id));
		bugReportVO.setProblem(problem);
		bugReportVO = createBugReport(bugReportVO);
		return bugReportVO;
	}

	@Override
	public List<BugReportVO> getBugReportByCient(ClientVO clientVO) throws Exception {
		List<BugReportVO> vos = bugReportConverter.toVO(bugReportDao.findByClient(clientConverter
				.toEntity(clientVO)));
		return vos;
	}

	@Override
	public List<BugReportVO> getBugReportByProductType(ProductTypeVO productTypeVO)
			throws Exception {
		List<BugReportVO> vos = bugReportConverter.toVO(bugReportDao
				.findByProductType(productTypeConverter.toEntity(productTypeVO)));
		return vos;
	}

	@Override
	public List<BugReportVO> getAllBugReport() throws Exception {
		List<BugReportVO> vos = bugReportConverter.toVO(bugReportDao.findAll());
		return vos;
	}

}
