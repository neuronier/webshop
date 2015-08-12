package hu.neuron.ier.core.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "BugReport")
public class BugReport extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Client client;
	private ProductType productType;
	private String problem;
	
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public ProductType getProductType() {
		return productType;
	}
	public void setProductType(ProductType productType) {
		this.productType = productType;
	}
	public String getProblem() {
		return problem;
	}
	public void setProblem(String problem) {
		this.problem = problem;
	}
	

	
}
