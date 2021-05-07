package zhegalov.course.work.service;

import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import zhegalov.course.work.model.GeneratorSetup;
import zhegalov.course.work.model.expression.AddExpression;
import zhegalov.course.work.model.expression.Expression;

public class ExpressionGeneratorServiceImpl implements ExpressionGeneratorService {

    @Override
    public Expression create(GeneratorSetup generatorSetup) {
        final var random = new Random();
        final var operationList = generatorSetup.getOperations();
        final var currentOperation = operationList.get(random.nextInt(operationList.size()));
        final var values = IntStream.range(0, generatorSetup.getValueCnt()).boxed()
                .map(unused -> getRandom(generatorSetup.getMin(), generatorSetup.getMax()))
                .collect(Collectors.toList());

        switch (currentOperation) {
        case SUM:
            return new AddExpression(values.toArray(new Integer[0]));

        default:
            throw new RuntimeException();
        }
    }

    private int getRandom(int min, int max) {
        final var random = new Random();
        final var bound = max - min;
        final var randInBound = (bound > 0) ? random.nextInt(bound) : 0;
        return min + randInBound;
    }
}
