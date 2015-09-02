package hu.neuron.ier.business.item;

import hu.neuron.ier.business.vo.ItemVO;

import java.util.List;

public interface ItemServiceRemote {

	ItemVO createItem(ItemVO itemVO) throws Exception;

	void deleteItem(Long id) throws Exception;
	
	ItemVO updateItemQuanty(Long id, int quanty) throws Exception;

	List<ItemVO> getAllItem() throws Exception;
}
