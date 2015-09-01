package hu.neuron.ier.business.item.impl;

import hu.neuron.ier.business.converter.ItemConverter;
import hu.neuron.ier.business.item.ItemServiceRemote;
import hu.neuron.ier.business.orderelement.OrderElementServiceRemote;
import hu.neuron.ier.business.vo.ItemVO;
import hu.neuron.ier.core.dao.ItemDao;


import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.Interceptors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ejb.interceptor.SpringBeanAutowiringInterceptor;

@Stateless(mappedName = "ItemService", name = "ItemService")
@Remote(ItemServiceRemote.class)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@Interceptors(SpringBeanAutowiringInterceptor.class)
public class ItemServiceImpl implements ItemServiceRemote, Serializable {

	private static final long serialVersionUID = 9024441429565077654L;

	@Autowired
	ItemDao itemDao;

	@EJB
	ItemConverter converter;
	

	@Override
	public void deleteItem(Long id) throws Exception {
		itemDao.delete(id);
	}

	@Override
	public ItemVO updateItemQuanty(Long id, int quanty) throws Exception {
		ItemVO itemVO = converter.toVO(itemDao.findOne(id));
		itemVO.setQuanty(quanty);
		createItem(itemVO);
		return itemVO;
	}

	@Override
	public List<ItemVO> getAllItem() throws Exception {
		List<ItemVO> vos = converter.toVO(itemDao.findAll());
		return vos;
	}

	@Override
	public ItemVO createItem(ItemVO itemVO) throws Exception {
		ItemVO vo = converter
				.toVO(itemDao.save(converter.toEntity(itemVO)));
		return vo;
	}


}
