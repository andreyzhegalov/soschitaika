package zhegalov.course.work.model.expression;

import java.util.List;

public class AddExpression extends Expression {

    public AddExpression(List<Integer> values) {
        super(values);
    }

    @Override
    public String getOperation() {
        return "+";
    }
}