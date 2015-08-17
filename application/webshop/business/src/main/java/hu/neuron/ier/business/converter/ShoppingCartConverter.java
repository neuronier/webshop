package hu.neuron.ier.business.converter;

import hu.neuron.ier.business.vo.ShoppingCartVO;
import hu.neuron.ier.core.entity.ShoppingCart;

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
public class ShoppingCartConverter {

	@Autowired
	@Qualifier("mapper")
	Mapper mapper;

	public ShoppingCartVO toVO(ShoppingCart shoppingCart) {
		if (shoppingCart == null) {
			return null;
		}

		return mapper.map(shoppingCart, ShoppingCartVO.class);
	}

	public ShoppingCart toEntity(ShoppingCartVO shoppingCartVO) {
		if (shoppingCartVO == null) {
			return null;
		}

		return mapper.map(shoppingCartVO, ShoppingCart.class);
	}

	public List<ShoppingCartVO> toVO(List<ShoppingCart> shoppingCarts) {
		if (shoppingCarts == null) {
			return null;
		}

		List<ShoppingCartVO> cartVOs = new ArrayList<ShoppingCartVO>();
		for (ShoppingCart shoppingCart : shoppingCarts) {
			cartVOs.add(toVO(shoppingCart));
		}

		return cartVOs;
	}

	public List<ShoppingCart> toEntity(List<ShoppingCartVO> cartVOs) {
		if (cartVOs == null) {
			return null;
		}

		List<ShoppingCart> carts = new ArrayList<ShoppingCart>();
		for (ShoppingCartVO cartVO : cartVOs) {
			carts.add(toEntity(cartVO));
		}

		return carts;
	}
}
