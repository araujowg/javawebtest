package com.dynamic.ent;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Deptos")
public class Deptos {

	private List<Depto> depto;

	@XmlElement(name="Depto")
	public List<Depto> getDepto() {
		return depto;
	}

	public void setDepto(List<Depto> depto) {
		this.depto = depto;
	}
}
