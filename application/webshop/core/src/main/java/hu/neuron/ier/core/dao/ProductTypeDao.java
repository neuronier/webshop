package hu.neuron.ier.core.dao;

import hu.neuron.ier.core.entity.ProductType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(propagation = Propagation.SUPPORTS)
public interface ProductTypeDao  extends JpaRepository<ProductType, Long> {

	ProductType findProductByItemNumber(String itemNumber) throws Exception;
}
