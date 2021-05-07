package zhegalov.course.work.model.expression;

import java.util.List;

import lombok.Data;

@Data
public abstract class Expression {
    private final List<Integer> values;

    protected Expression(List<Integer> values) {
        this.values = values;
    }

    public abstract String getOperation();

    public abstract Integer getResult();
}
