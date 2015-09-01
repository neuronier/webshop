package hu.neuron.ier.business.converter;

import hu.neuron.ier.business.vo.ItemVO;
import hu.neuron.ier.business.vo.OrderElementVO;
import hu.neuron.ier.core.dao.ItemDao;


import hu.neuron.ier.core.entity.Item;
import hu.neuron.ier.core.entity.OrderElement;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Singleton;
import javax.interceptor.Interceptors;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ejb.interceptor.SpringBeanAutowiringInterceptor;

@Singleton
@Interceptors(SpringBeanAutowiringInterceptor.class)
public class ItemConverter {

	@Autowired
	@Qualifier("mapper")
	Mapper mapper;

	public ItemVO toVO(Item item) {
		if (item == null) {
			return null;
		}
		return mapper.map(item, ItemVO.class);
	}

	public Item toEntity(ItemVO itemVO) {
		if (itemVO == null) {
			return null;
		}

		return mapper.map(itemVO, Item.class);

	}

	public List<ItemVO> toVO(List<Item> items) {
		if (items == null) {
			return null;
		}

		List<ItemVO> itemVOs = new ArrayList<ItemVO>();
		for (Item item : items) {
			itemVOs.add(toVO(item));
		}

		return itemVOs;
	}

	public List<Item> toEntity(List<ItemVO> items) {
		if (items == null) {
			return null;
		}

		List<Item> item = new ArrayList<Item>();
		for (ItemVO itemVO : items) {
			item.add(toEntity(itemVO));
		}

		return item;
	}
}
