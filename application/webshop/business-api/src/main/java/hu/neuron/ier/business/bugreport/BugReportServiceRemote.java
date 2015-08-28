package hu.neuron.ier.business.bugreport;

import hu.neuron.ier.business.vo.BugReportVO;

import java.util.List;

public interface BugReportServiceRemote {

	public enum Status {
		NEW {
			@Override
			public String toString() {
				return "New";
			}
		},
		ONGOING {
			@Override
			public String toString() {
				return "Ongoing";
			}
		},
		RESOLVED {
			@Override
			public String toString() {
				return "Resolved";
			}
		}
	}

	public BugReportVO saveBugReport(BugReportVO bugReport);

	public void removeBugReport(BugReportVO bugReport);

	public BugReportVO findByReportId(String reportId);

	public List<BugReportVO> findByClientId(String clientId);

	public int getBugReportCount();

	public List<BugReportVO> getBugReportList(int i, int pageSize, String sortField, int dir, String filter, String filterColumnName);

	public long countOngoingBugReport();

}
