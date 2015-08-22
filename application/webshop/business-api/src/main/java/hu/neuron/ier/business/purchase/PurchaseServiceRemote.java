package hu.neuron.ier.business.purchase;

import hu.neuron.ier.business.vo.ClientVO;
import hu.neuron.ier.business.vo.PurchaseVO;

import java.util.Calendar;
import java.util.List;

public interface PurchaseServiceRemote {

	PurchaseVO createPurchase(PurchaseVO purchaseVO) throws Exception;

	void deletePurchase(Long id) throws Exception;

	List<PurchaseVO> getPurchaseByClient(ClientVO clientVO) throws Exception;

	List<PurchaseVO> getAllPurchase() throws Exception;

	List<PurchaseVO> getPurchaseByDate(Calendar date) throws Exception;

	List<PurchaseVO> getPurchaseByStatus(String status) throws Exception;
}
