package ru.zhegalov.course.work.service;

import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import ru.zhegalov.course.work.model.expression.Expression;

@Service
public class ExpressionPrintServiceImpl implements ExpressionPrintService {

    @Override
    public String print(Expression expression) {
        return expression.getValues().stream()
            .map(Object::toString).collect(Collectors.joining(expression.getOperation()));
    }

}
