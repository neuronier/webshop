package hu.neuron.ier.business.vo;

import java.io.Serializable;

public class BugReportVO implements Serializable {

	private static final long serialVersionUID = -4549929299614172482L;

	private Long id;
	private ClientVO clientVO;
	private ProductTypeVO productTypeVO;
	private String problem;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ClientVO getClientVO() {
		return clientVO;
	}

	public void setClientVO(ClientVO clientVO) {
		this.clientVO = clientVO;
	}

	public ProductTypeVO getProductTypeVO() {
		return productTypeVO;
	}

	public void setProductTypeVO(ProductTypeVO productTypeVO) {
		this.productTypeVO = productTypeVO;
	}

	public String getProblem() {
		return problem;
	}

	public void setProblem(String problem) {
		this.problem = problem;
	}

}
