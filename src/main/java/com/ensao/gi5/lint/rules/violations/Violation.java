package com.ensao.gi5.lint.rules.violations;

import com.ensao.gi5.lint.rules.Level;

import java.util.Comparator;
import java.util.Objects;

public class Violation implements Comparable<Violation>{
	private String description;
	private String ruleId;
	private String fileName;
	private int line;
    private Level level;

    public Violation addRuleId(String ruleId){
        this.ruleId = ruleId;
        return this;
    }

    public Violation addLevel(Level level){
        this.level = level;
        return this;
    }

    public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public void setLine(int line) {
		this.line = line;
	}

	public int getLine() {
		return line;
	}

	public String getFileName() {
		return fileName;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

    public void setRuleId(String ruleId) {
        this.ruleId = ruleId;
    }

    public String toString(){
		return level+"\t[" +ruleId +"] " + description + " at '" +fileName +":"+ line+"'";
	}

    @Override
    public int compareTo(Violation o) {
        Comparator<Violation> byFileName = Comparator.comparing(Violation::getFileName, Comparator.nullsLast(String::compareTo));
        Comparator<Violation> byLine = Comparator.comparing(Violation::getLine);
        Comparator<Violation> byLevel = Comparator.comparing(Violation::getLevel, Comparator.nullsLast(Enum::compareTo));
        Comparator<Violation> byDescription = Comparator.comparing(Violation::getDescription, Comparator.nullsLast(String::compareTo));
        return byFileName
                .thenComparing(byLine)
                .thenComparing(byLevel)
                .thenComparing(byDescription)
                .compare(this, o);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Violation)) {
            return false;
        }

        Violation violation = (Violation) o;
        return getLine() == violation.getLine()
                && Objects.equals(ruleId, violation.ruleId)
                && Objects.equals(getFileName(), violation.getFileName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(ruleId, getFileName(), getLine());
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public Level getLevel() {
        return level;
    }

    public String getRuleId() {
        return ruleId;
    }
}
