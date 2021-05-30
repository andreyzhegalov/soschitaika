package ru.zhegalov.course.work.service.inner;

import java.util.List;

import ru.zhegalov.course.work.model.expression.DivExpression;
import ru.zhegalov.course.work.model.expression.MulExpression;
import ru.zhegalov.course.work.model.expression.SumExpression;
import ru.zhegalov.course.work.service.ExpressionServiceException;
import ru.zhegalov.course.work.model.expression.Expression;
import ru.zhegalov.course.work.model.expression.ExpressionOperation;
import ru.zhegalov.course.work.model.expression.SubExpression;

public class ExpressionFactory {

    public static Expression create(ExpressionOperation operationType, List<Integer> values) {
        switch (operationType) {
            case SUM:
                return new SumExpression(values);
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

