package zhegalov.course.work.model.expression;

import java.util.List;

public class MulExpression extends Expression{

    public MulExpression(List<Integer> values) {
        super(values);
    }

    @Override
    public String getOperation() {
        return "*";
    }

}

