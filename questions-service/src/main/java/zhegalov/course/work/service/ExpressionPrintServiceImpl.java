package zhegalov.course.work.service;

import java.util.stream.Collectors;

import zhegalov.course.work.model.expression.Expression;


public class ExpressionPrintServiceImpl implements ExpressionPrintService {

    @Override
    public String print(Expression expression) {
        return expression.getValues().stream()
            .map(Object::toString).collect(Collectors.joining(expression.getOperation()));
    }

}
