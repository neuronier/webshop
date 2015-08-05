package hu.neuron.ier.core;

import java.io.Serializable;

public class RoleVO implements Serializable {

	private static final long serialVersionUID = 1487422163671957262L;

	private Long id;
	private String name;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
