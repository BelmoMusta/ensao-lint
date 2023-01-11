package com.ensao.gi5.lint.wrapper;

import com.github.javaparser.ast.nodeTypes.NodeWithCondition;

public class BooleanExpressionWrapper extends AbstractWrapper {
    private final String condition;
    private final String conditionalStatementType;

    public BooleanExpressionWrapper(NodeWithCondition<?> node, String conditionalStatementType) {
        super(node.getCondition().getBegin().map(p -> p.line).orElse(-1));
        this.condition = node.getCondition().toString();
        this.conditionalStatementType = conditionalStatementType;
    }

    public String getCondition() {
        return this.condition;
    }

    @Override
    public String toString() {
        return "'" + this.condition + "' within '" + this.conditionalStatementType + "'";
    }
}
