package hu.neuron.ier.web;
import hu.neuron.ier.business.admin.AdminServiceRemote;
import hu.neuron.ier.business.vo.UserVO;

import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

public class LazyUserModel extends LazyDataModel<UserVO> {

	private static final long serialVersionUID = 1L;

	private AdminServiceRemote adminService = null;
	private List<UserVO> visibleUserList;

	public LazyUserModel(AdminServiceRemote adminService) {
		this.adminService = adminService;

	}

	@Override
	public UserVO getRowData(String rowkey) {
		if (visibleUserList != null || rowkey != null) {
			for (UserVO user : visibleUserList) {
				if (user.getId().toString().equals(rowkey)) {
					return user;
				}
			}
		}
		return null;
	}

	@Override
	public Object getRowKey(UserVO user) {
		if (user == null) {
			return null;
		}
		return user.getId();
	}

	@Override
	public List<UserVO> load(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, Object> filters) {
		String filter = "";
		String filterColumnName = "";
		if (filters.keySet().size() > 0) {
			filter = (String) filters.values().toArray()[0];
			filterColumnName = filters.keySet().iterator().next();
		}
		if (sortField == null) {
			sortField = "userName";
		}
		try {
			int dir = sortOrder.equals(SortOrder.ASCENDING) ? 1 : 2;
			visibleUserList = adminService.getUserList(first / pageSize, pageSize,
					sortField, dir, filter, filterColumnName);
			int dataSize = adminService.getRowNumber();
			this.setRowCount(dataSize);
		} catch (Exception e) {
			// TODO: handle exception
		}
		

		return visibleUserList;
	}

	public List<UserVO> getVisibleUserList() {
		return visibleUserList;
	}

	public void setVisibleUserList(List<UserVO> visibleUserList) {
		this.visibleUserList = visibleUserList;
	}
	
	

}