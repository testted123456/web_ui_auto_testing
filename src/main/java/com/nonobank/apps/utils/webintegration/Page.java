package com.nonobank.apps.utils.webintegration;

import java.util.List;

public class Page {
	String name;
	String desc;
	String dependence;
	boolean isDisabled;
	public boolean isDisabled() {
		return isDisabled;
	}
	public void setDisabled(boolean isDisabled) {
		this.isDisabled = isDisabled;
	}
	List<Methed> methods;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getDependence() {
		return dependence;
	}
	public void setDependence(String dependence) {
		this.dependence = dependence;
	}
	public List<Methed> getMethods() {
		return methods;
	}
	public void setMethods(List<Methed> methods) {
		this.methods = methods;
	}
	
}
