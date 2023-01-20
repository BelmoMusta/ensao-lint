package com.ensao.gi5.lint.printer;

public enum ConsoleColors {
	// Using ANSI escape code
	BLUE_UNDERLINED("\033[4;34m"), 
	GREEN("\033[1;32m"),
	PURPLE("\033[1;35m"),
	RED("\033[1;31m"),
	RESET("\033[0m"), 
	YELLOW("\033[1;33m");
	
	private final String color; 
	
	private ConsoleColors(String color) {
		this.color = color;
	}

	public String getColor() {
		return color;
	}
	
}
