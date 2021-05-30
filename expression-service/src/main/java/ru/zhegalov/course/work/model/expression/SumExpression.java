package ru.zhegalov.course.work.model.expression;

import java.util.List;

public class SumExpression extends Expression {

    public SumExpression(List<Integer> values) {
        super(values);
    }

    @Override
    public String getOperation() {
        return "+";
    }

    @Override
    public Integer getResult() {
        final var iterator = getValues().iterator();
        Integer result = iterator.next();
        while (iterator.hasNext()) {
            result += iterator.next();
        }
        return result;
    }
}
