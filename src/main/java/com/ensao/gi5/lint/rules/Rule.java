package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;

import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public abstract class Rule {
    private final String id;
    private final Level level;

    protected final Set<Violation> violations = new TreeSet<>();

    protected Rule(String id, Level level) {
        this.id = id;
        this.level = level;
    }

    public final void addViolation(Violation violation) {
        violation.setRuleId(getId());
        violation.setLevel(getLevel());
        violations.add(violation);
    }

    public abstract void apply(CompilationUnitWrapper compilationUnit);

    public Set<Violation> getViolations() {
        return violations;
    }

    public String getId() {
        return id;
    }

    public Level getLevel() {
        return level;
    }

    public abstract boolean isActive();

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Rule)) {
            return false;
        }
        Rule rule = (Rule) o;
        return Objects.equals(getId(), rule.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }
}
