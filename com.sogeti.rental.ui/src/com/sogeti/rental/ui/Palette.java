package com.sogeti.rental.ui;

import org.eclipse.jface.viewers.IColorProvider;

public class Palette {

	private String id, name;
	private IColorProvider cp;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public IColorProvider getCp() {
		return cp;
	}

	public void setCp(IColorProvider cp) {
		this.cp = cp;
	}
}
