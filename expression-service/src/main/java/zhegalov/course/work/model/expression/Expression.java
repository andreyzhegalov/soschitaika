package zhegalov.course.work.model.expression;

import java.util.List;

import lombok.Data;

@Data
public abstract class Expression {
    private final List<Integer> values;

    protected Expression(Integer ... values) {
        this.values = List.of(values);
    }

    public abstract String getOperation();
}

