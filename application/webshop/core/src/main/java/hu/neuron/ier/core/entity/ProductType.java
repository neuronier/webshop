package hu.neuron.ier.core.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "ProductType", uniqueConstraints = @UniqueConstraint(columnNames = { "itemNumber" }))
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
