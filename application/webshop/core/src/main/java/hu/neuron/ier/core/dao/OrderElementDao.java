package hu.neuron.ier.core.dao;

import java.util.List;

import hu.neuron.ier.core.entity.OrderElement;
import hu.neuron.ier.core.entity.ProductType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(propagation = Propagation.SUPPORTS)
public interface OrderElementDao extends JpaRepository<OrderElement, Long>{

	List<OrderElement> findOrderElementByProductType(ProductType productType) throws Exception;
		
}
