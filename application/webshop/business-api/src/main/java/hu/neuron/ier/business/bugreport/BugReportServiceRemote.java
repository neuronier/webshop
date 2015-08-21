package hu.neuron.ier.business.bugreport;

import hu.neuron.ier.business.vo.BugReportVO;
import hu.neuron.ier.business.vo.ClientVO;
import hu.neuron.ier.business.vo.ProductTypeVO;

import java.util.List;

public interface BugReportServiceRemote {

	BugReportVO createBugReport(BugReportVO bugReportVO) throws Exception;

	void deleteBugReport(Long id) throws Exception;

	BugReportVO updateProblem(Long id, String problem) throws Exception;

	List<BugReportVO> getBugReportByCient(ClientVO clientVO) throws Exception;

	List<BugReportVO> getBugReportByProductType(ProductTypeVO productTypeVO) throws Exception;

	List<BugReportVO> getAllBugReport() throws Exception;

}
