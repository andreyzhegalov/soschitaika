package zhegalov.course.work.service.inner;

import java.util.List;

import zhegalov.course.work.model.expression.AddExpression;
import zhegalov.course.work.model.expression.DivExpression;
import zhegalov.course.work.model.expression.Expression;
import zhegalov.course.work.model.expression.ExpressionOperation;
import zhegalov.course.work.model.expression.MulExpression;
import zhegalov.course.work.model.expression.SubExpression;
import zhegalov.course.work.service.ExpressionServiceException;

public class ExpressionFactory {

    public static Expression create(ExpressionOperation operationType, List<Integer> values) {
        switch (operationType) {
            case SUM:
                return new AddExpression(values);
            case SUB:
                return new SubExpression(values);
            case MUL:
                return new MulExpression(values);
            case DIV:
                return new DivExpression(values);
            default:
                throw new ExpressionServiceException("operation not defined");
        }
    }
}

