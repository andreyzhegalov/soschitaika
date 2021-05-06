package zhegalov.course.work.service;

import java.util.Random;

import zhegalov.course.work.model.AddExpression;
import zhegalov.course.work.model.Expression;
import zhegalov.course.work.model.GeneratorSetup;

public class ExpressionGeneratorServiceImpl implements ExpressionGeneratorService {

    @Override
    public Expression create(GeneratorSetup generatorSetup) {
        final var random = new Random();
        final var operationList = generatorSetup.getOperations();
        final var currentOperation = operationList.get(random.nextInt(operationList.size()));
        final var firstValue =  getRandom(generatorSetup.getMin(), generatorSetup.getMax());
        final var secondValue = getRandom(generatorSetup.getMin(), generatorSetup.getMax());

        switch (currentOperation) {
            case SUM:
                return new AddExpression(firstValue, secondValue);

            default:
                throw new RuntimeException();
        }
    }

    private int getRandom(int min, int max){
        final var random = new Random();
        final var bound = max -min;
        final var randInBound = (bound > 0) ? random.nextInt(bound) : 0;
        return min + randInBound;
    }
}
