package zhegalov.course.work.model.expression;

import java.util.List;

public class DivExpression extends Expression {

    public DivExpression(List<Integer> values) {
        super(values);
    }

    @Override
    public String getOperation() {
        return "/";
    }

}
