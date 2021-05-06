package zhegalov.course.work.model.expression;

public class SubExpression extends Expression {

    public SubExpression(Integer... values) {
        super(values);
    }

    @Override
    public String getOperation() {
        return "-";
    }

}
