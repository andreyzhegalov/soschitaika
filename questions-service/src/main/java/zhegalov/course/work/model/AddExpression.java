package zhegalov.course.work.model;

import java.util.List;

public class AddExpression extends Expression{
    private final List<Integer> values;

    public AddExpression(Integer ... values) {
        this.values = List.of(values);
    }


}

