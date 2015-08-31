package hu.neuron.ier.web;

import hu.neuron.ier.business.vo.OfferVO;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "dataGridView")
@SessionScoped
public class DataGridView implements Serializable {

	private static final long serialVersionUID = 32172141178229266L;

	private List<OfferVO> offers;

	private OfferVO selectedOffer;

	@ManagedProperty("#{offerController}")
	private OfferController service;

	@ManagedProperty("#{fieldSetSessionBean}")
	private FieldSetSessionBean fieldSetSessionBean;

	@PostConstruct
	public void init() {
		offers = service.createOffers();
		fieldSetSessionBean.setFieldSetLegend("Kiemelt Ajánlatok");
	}

	public List<OfferVO> getOffers() {
		return offers;
	}

	public void setOffers(List<OfferVO> offers) {
		this.offers = offers;
	}

	public OfferVO getSelectedOffer() {
		return selectedOffer;
	}

	public void setSelectedOffer(OfferVO selectedOffer) {
		this.selectedOffer = selectedOffer;
	}

	public OfferController getService() {
		return service;
	}

	public void setService(OfferController service) {
		this.service = service;
	}

	public FieldSetSessionBean getFieldSetSessionBean() {
		return fieldSetSessionBean;
	}

	public void setFieldSetSessionBean(FieldSetSessionBean fieldSetSessionBean) {
		this.fieldSetSessionBean = fieldSetSessionBean;
	}

}