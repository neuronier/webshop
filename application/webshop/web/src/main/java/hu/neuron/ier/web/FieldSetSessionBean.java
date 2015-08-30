package hu.neuron.ier.web;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@SessionScoped
@ManagedBean(name="fieldSetSessionBean")
public class FieldSetSessionBean implements Serializable {

	private static final long serialVersionUID = 6029792477165767903L;
	private String fieldSetLegend;

	public String getFieldSetLegend() {
		return fieldSetLegend;
	}

	public void setFieldSetLegend(String fieldSetLegend) {
		this.fieldSetLegend = fieldSetLegend;
	}

}
