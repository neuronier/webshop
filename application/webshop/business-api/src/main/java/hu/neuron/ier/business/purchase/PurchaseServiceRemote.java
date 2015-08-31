package hu.neuron.ier.business.purchase;

import hu.neuron.ier.business.vo.ClientVO;
import hu.neuron.ier.business.vo.OfferVO;
import hu.neuron.ier.business.vo.PurchaseVO;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

public interface PurchaseServiceRemote {

	PurchaseVO createPurchase(PurchaseVO purchaseVO) throws Exception;

	void deletePurchase(Long id) throws Exception;

	List<PurchaseVO> getPurchaseByClient(ClientVO clientVO) throws Exception;

	List<PurchaseVO> getAllPurchase() throws Exception;

	List<PurchaseVO> getPurchaseByDate(Calendar date) throws Exception;

	List<PurchaseVO> getPurchaseByStatus(String status) throws Exception;
	
	public void addOffersToPurchace(Map<OfferVO, Long> map, PurchaseVO purchaseVO);

	PurchaseVO getPurchaseById(Long id) throws Exception;
}
