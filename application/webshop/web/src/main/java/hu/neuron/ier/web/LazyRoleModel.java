package hu.neuron.ier.web;
import hu.neuron.ier.business.admin.AdminServiceRemote;
import hu.neuron.ier.business.vo.RoleVO;

import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

public class LazyRoleModel extends LazyDataModel<RoleVO> {

	private List<RoleVO> visibleRoleList;

	private AdminServiceRemote adminService;

	public LazyRoleModel(AdminServiceRemote adminService) {
		super();
		this.adminService = adminService;
	}

	@Override
	public RoleVO getRowData(String rowkey) {
		if (visibleRoleList != null || rowkey != null) {
			for (RoleVO roleVO : visibleRoleList) {
				if (roleVO.getId().toString().equals(rowkey)) {
					return roleVO;
				}
			}
		}
		return null;
	}

	@Override
	public Object getRowKey(RoleVO roleVO) {
		if (roleVO == null) {
			return null;
		}
		return roleVO.getId();
	}

	@Override
	public List<RoleVO> load(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, Object> filters) {
		try {
			String filter = "";
			String filterColumnName = "";
			if (filters.keySet().size() > 0) {
				filter = (String) filters.values().toArray()[0];
				filterColumnName = filters.keySet().iterator().next();
			}
			if (sortField == null) {
				sortField = "name";
			}
			
			int dir = sortOrder.equals(SortOrder.ASCENDING) ? 1 : 2;
			visibleRoleList = adminService.getRoles(first / pageSize, pageSize,
					sortField, dir, filter, filterColumnName);

			int dataSize = adminService.getRoleCount();

			this.setRowCount(dataSize);
		} catch (Exception e) {
			// TODO: handle exception
		}
		

		return visibleRoleList;

	}

	public AdminServiceRemote getAdminService() {
		return adminService;
	}

	public void setAdminService(AdminServiceRemote adminService) {
		this.adminService = adminService;
	}

}