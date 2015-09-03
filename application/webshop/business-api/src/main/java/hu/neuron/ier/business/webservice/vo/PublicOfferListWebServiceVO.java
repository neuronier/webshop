package hu.neuron.ier.business.webservice.vo;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="PublicOfferList")
public class PublicOfferListWebServiceVO implements Serializable {

	private static final long serialVersionUID = -3732378265492377038L;
	
	@XmlElement(name="publicOffers")
	private List<PublicOfferWebServiceVO> publicOffers;

	public List<PublicOfferWebServiceVO> getPublicOffers() {
		return publicOffers;
	}

	public void setPublicOffers(List<PublicOfferWebServiceVO> publicOffers) {
		this.publicOffers = publicOffers;
	}

}
