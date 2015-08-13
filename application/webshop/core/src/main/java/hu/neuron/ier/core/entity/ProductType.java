package hu.neuron.ier.core.entity;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "ProductType")
@NamedQuery(name = "ProductType.findByProductitemNumber", query = "SELECT p FROM ProductType p  WHERE p.itemNumber = :itemNumber")
public class ProductType extends BaseEntity {

	private static final long serialVersionUID = 5348206419265390939L;
	private String itemNumber;

	public String getItemNumber() {
		return itemNumber;
	}

	public void setItemNumber(String itemNumber) {
		this.itemNumber = itemNumber;
	}

}
