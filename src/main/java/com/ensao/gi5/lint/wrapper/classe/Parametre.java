package com.ensao.gi5.lint.wrapper.classe;

public class Parametre {
	
	final private String name;
	final private String type;

	public Parametre(String name, String type) {
		this.name = name;
		this.type = type;
	}

	public String getName() {
		return name;
	}
	
	public String getType() {
		return type;
	}

	@Override
	public String toString() {
		return this.type + " " + this.name;
	}

}
