package com.ensao.gi5.lint.wrapper;

public class ElementWrapper {

	private String name;
	private int line;
	
	public ElementWrapper(String name,int line) {
		this.line=line;
		this.name=name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public int getLine() {
		return line;
	}

	public void setLine(int line) {
		this.line = line;
	}

	@Override
	public String toString() {
		return this.name;
	}

}
