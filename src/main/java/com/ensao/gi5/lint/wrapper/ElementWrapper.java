package com.ensao.gi5.lint.wrapper;

public abstract class ElementWrapper {

	protected String name;
	protected int line;

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
