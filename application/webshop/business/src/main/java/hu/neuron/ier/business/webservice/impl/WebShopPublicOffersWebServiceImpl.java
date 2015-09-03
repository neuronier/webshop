package hu.neuron.ier.business.webservice.impl;

import hu.neuron.ier.business.converter.PublicOfferConverter;
import hu.neuron.ier.business.webservice.WebShopPublicOffersWebService;
import hu.neuron.ier.business.webservice.vo.PublicOfferListWebServiceVO;
import hu.neuron.ier.business.webservice.vo.PublicOfferWebServiceVO;
import hu.neuron.ier.core.dao.OfferDao;
import hu.neuron.ier.core.entity.Offer;

import java.util.List;

import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ejb.interceptor.SpringBeanAutowiringInterceptor;

@Stateless(mappedName = "PublicOffersWebService", name = "PublicOffersWebService")
@WebService(name = "WebShopPublicOffersWebServicePort", serviceName = "WebShopPublicOffersWebService", targetNamespace = "http://hu.neuron", endpointInterface = "hu.neuron.ier.business.webservice.WebShopPublicOffersWebService")
@Interceptors(SpringBeanAutowiringInterceptor.class)
public class WebShopPublicOffersWebServiceImpl implements WebShopPublicOffersWebService{
	@Autowired
	OfferDao offerDao;
	
	@Autowired
	PublicOfferConverter converter;
	
	@Override
	public PublicOfferListWebServiceVO getPublicOfferListByNameWebMethod(
			String name) {
		//vo objektum létrehozása
		PublicOfferListWebServiceVO vo= new PublicOfferListWebServiceVO();
		//lista létrehozása
		List<PublicOfferWebServiceVO> lista;
		try {
			//ajánlatok lekérdezése
			List<Offer> offers = offerDao.findOfferByName(name);
			//ajánlatok átkonvertálása
			lista = converter.toVO(offers);
			//vo objektum listájának beállítása
			vo.setPublicOffers(lista);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		//vo objektum visszaadása
		return vo;
	}

	@Override
	public PublicOfferListWebServiceVO getAllPublicOfferWebMethod() {
		//vo objektum létrehozása
		PublicOfferListWebServiceVO vo = new PublicOfferListWebServiceVO();
		//lista létrehozása
		List<PublicOfferWebServiceVO> lista;
		//ajánlatok lekérdezése
		List<Offer> offers = offerDao.findAll();
		//ajánlatok átkonvertálása
		lista = converter.toVO(offers);
		//vo objektum listájának beállítása
		vo.setPublicOffers(lista);
		
		//vo objektum visszaadása
		return vo;
	}

}
