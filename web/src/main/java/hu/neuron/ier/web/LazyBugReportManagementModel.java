package hu.neuron.ier.web;
import hu.neuron.ier.business.bugreport.BugReportServiceRemote;
import hu.neuron.ier.business.vo.BugReportVO;
import hu.neuron.ier.business.vo.UserVO;

import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

public class LazyBugReportManagementModel extends LazyDataModel<BugReportVO> {

	

	private static final long serialVersionUID = -4091722103838409971L;

	private List<BugReportVO> visibleBugReportList;
	
	private BugReportServiceRemote bugReportService;

	public LazyBugReportManagementModel(BugReportServiceRemote bugReportService) {
		super();
		this.bugReportService = bugReportService;
	}

	@Override
	public BugReportVO getRowData(String rowkey) {
		if (visibleBugReportList != null || rowkey != null) {
			for (BugReportVO bugReportVO : visibleBugReportList) {
				if (bugReportVO.getReportId().equals(rowkey)) {
					return bugReportVO;
				}
			}
		}
		return null;
	}

	@Override
	public Object getRowKey(BugReportVO bugReportVO) {
		if (bugReportVO == null) {
			return null;
		}
		return bugReportVO.getReportId();
	}

	@Override
	public List<BugReportVO> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
		String filter = "";
		String filterColumnName = "";
		if (filters.keySet().size() > 0) {
			filter = (String) filters.values().toArray()[0];
			filterColumnName = filters.keySet().iterator().next();
		}
		if (sortField == null) {
			sortField = "clientId";
		}

		int dir = sortOrder.equals(SortOrder.ASCENDING) ? 1 : 2;
		visibleBugReportList = bugReportService.getBugReportList(first / pageSize, pageSize, sortField, dir, filter, filterColumnName);

		int dataSize = bugReportService.getBugReportCount();

		this.setRowCount(dataSize);

		return visibleBugReportList;
	}


	public List<BugReportVO> getVisibleBugReportList() {
		return visibleBugReportList;
	}

	public void setVisibleBugReportList(List<BugReportVO> visibleBugReportList) {
		this.visibleBugReportList = visibleBugReportList;
	}

	public BugReportServiceRemote getBugReportService() {
		return bugReportService;
	}

	public void setBugReportService(BugReportServiceRemote bugReportService) {
		this.bugReportService = bugReportService;
	}

	
	
	

}