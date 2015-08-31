package hu.neuron.ier.web;
import hu.neuron.ier.business.offer.OfferServiceRemote;
import hu.neuron.ier.business.vo.OfferVO;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

public class LazyOfferClientModel extends LazyDataModel<OfferVO> {

	private static final long serialVersionUID = 1L;

	private OfferServiceRemote offerService;
	

	private List<OfferVO> visibleOfferList;

	public LazyOfferClientModel(OfferServiceRemote offerService) {
		super();
		this.offerService = offerService;
	}
	
	@Override
	public OfferVO getRowData(String rowkey) {
		if (visibleOfferList != null || rowkey != null) {
			for (OfferVO offer : visibleOfferList) {
				if (offer.getId().toString().equals(rowkey)) {
					return offer;
				}
			}
		}
		return null;
	}

	@Override
	public Object getRowKey(OfferVO offer) {
		if (offer == null) {
			return null;
		}
		return offer.getId();
	}

	@Override
	public List<OfferVO> load(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, Object> filters) {
		String filter = "";
		String filterColumnName = "";
		if (filters.keySet().size() > 0) {
			filter = (String) filters.values().toArray()[0];
			filterColumnName = filters.keySet().iterator().next();
		}
		if (sortField == null) {
			sortField = "name";
		}
		try {
			int dir = sortOrder.equals(SortOrder.ASCENDING) ? 1 : 2;
			visibleOfferList = offerService.getOfferList(first / pageSize, pageSize,
					sortField, dir, filter, filterColumnName);
			int dataSize = offerService.getRowNumber();
			this.setRowCount(dataSize);
		} catch (Exception e) {
			// TODO: handle exception
		}
		

		return visibleOfferList;
	}

	public List<OfferVO> getVisibleOfferList() {
		return visibleOfferList;
	}

	public void setVisibleOfferList(List<OfferVO> visibleOfferList) {
		this.visibleOfferList = visibleOfferList;
	}
	public OfferServiceRemote getOfferService() {
		return offerService;
	}

	public void setOfferService(OfferServiceRemote offerService) {
		this.offerService = offerService;
	}
	

}