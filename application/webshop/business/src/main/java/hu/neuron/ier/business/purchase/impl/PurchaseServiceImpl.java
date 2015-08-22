package hu.neuron.ier.business.purchase.impl;

import hu.neuron.ier.business.converter.ClientConverter;
import hu.neuron.ier.business.converter.PurchaseConverter;
import hu.neuron.ier.business.purchase.PurchaseServiceRemote;
import hu.neuron.ier.business.vo.ClientVO;
import hu.neuron.ier.business.vo.PurchaseVO;
import hu.neuron.ier.core.dao.ClientDao;
import hu.neuron.ier.core.dao.PurchaseDao;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.Interceptors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ejb.interceptor.SpringBeanAutowiringInterceptor;

@Stateless(mappedName = "PurchaseService", name = "PurchaseService")
@Remote(PurchaseServiceRemote.class)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@Interceptors(SpringBeanAutowiringInterceptor.class)
public class PurchaseServiceImpl implements PurchaseServiceRemote, Serializable {

	private static final long serialVersionUID = 426566156488656640L;

	@Autowired
	PurchaseDao purchaseDao;
	@Autowired
	ClientDao clientDao;

	@EJB
	PurchaseConverter purchaseConverter;
	@EJB
	ClientConverter clientConverter;

	@Override
	public PurchaseVO createPurchase(PurchaseVO purchaseVO) throws Exception {
		PurchaseVO vo = purchaseConverter.toVO(purchaseDao.save(purchaseConverter
				.toEntity(purchaseVO)));
		return vo;
	}

	@Override
	public void deletePurchase(Long id) throws Exception {
		purchaseDao.delete(id);

	}

	@Override
	public List<PurchaseVO> getPurchaseByClient(ClientVO clientVO) throws Exception {
		List<PurchaseVO> vos = purchaseConverter.toVO(purchaseDao.findByClient(clientConverter
				.toEntity(clientVO)));
		return vos;
	}

	@Override
	public List<PurchaseVO> getAllPurchase() throws Exception {
		List<PurchaseVO> vos = purchaseConverter.toVO(purchaseDao.findAll());
		return vos;
	}

	@Override
	public List<PurchaseVO> getPurchaseByDate(Calendar date) throws Exception {
		List<PurchaseVO> vos = purchaseConverter.toVO(purchaseDao.findByDate(date));
		return vos;
	}

	@Override
	public List<PurchaseVO> getPurchaseByStatus(String status) throws Exception {
		List<PurchaseVO> vos = purchaseConverter.toVO(purchaseDao.findByStatus(status));
		return vos;
	}

}
