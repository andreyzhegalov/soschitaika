package zhegalov.course.work.model.expression;

import java.util.List;

public class SubExpression extends Expression {
    public SubExpression(List<Integer> values) {
        super(values);
    }

    @Override
    public String getOperation() {
        return "-";
    }

}
