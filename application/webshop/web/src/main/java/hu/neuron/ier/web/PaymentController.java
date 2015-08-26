package hu.neuron.ier.web;

import hu.neuron.ier.business.vo.OfferVO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

@ViewScoped
@ManagedBean(name = "paymentController")
public class PaymentController implements Serializable {

	private static final long serialVersionUID = 3397706819780203623L;

	@ManagedProperty("#{shoppingCartController}")
	private ShoppingCartController shoppingCartController;

	
	
	public int totalPrice() {
		List<OfferVO> offers = new ArrayList<OfferVO>();
		offers.addAll(shoppingCartController.getOffers());
		int price = 0;
		for (OfferVO offerVO : offers) {
			price += offerVO.getNewCost();
		}

		return price;
	}

	public ShoppingCartController getShoppingCartController() {
		return shoppingCartController;
	}

	public void setShoppingCartController(ShoppingCartController shoppingCartController) {
		this.shoppingCartController = shoppingCartController;
	}

}
