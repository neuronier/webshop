package hu.neuron.ier.business.converter;

import hu.neuron.ier.business.webservice.vo.PublicOfferWebServiceVO;
import hu.neuron.ier.core.entity.Offer;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Singleton;

@Singleton
public class PublicOfferConverter {
	
	public PublicOfferWebServiceVO toVO(Offer offer){
		
		if(offer == null){
			return null;
		}
		
		PublicOfferWebServiceVO publicOffer = new PublicOfferWebServiceVO();
		publicOffer.setAction(offer.isAction());
		publicOffer.setCost(offer.getNewCost());
		publicOffer.setDescription(offer.getDescription());
		publicOffer.setFeatured(offer.isFeatured());
		publicOffer.setName(offer.getName());
		
		return publicOffer;
	}
	
	public List<PublicOfferWebServiceVO> toVO(List<Offer> offerList){
		
		if(offerList == null){
			return null;
		}
		
		List<PublicOfferWebServiceVO> poList = new ArrayList<PublicOfferWebServiceVO>();
		
		for(Offer offer : offerList){
			poList.add(toVO(offer));
		}
		
		return poList;
	}

}
