package zhegalov.course.work.model.expression;

public class AddExpression extends Expression {

    public AddExpression(Integer... values) {
        super(values);
    }

    @Override
    public String getOperation() {
        return "+";
    }
}
