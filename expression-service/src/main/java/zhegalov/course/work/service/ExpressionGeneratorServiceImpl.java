package zhegalov.course.work.service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import zhegalov.course.work.model.GeneratorSetup;
import zhegalov.course.work.model.expression.Expression;
import zhegalov.course.work.model.expression.ExpressionOperation;
import zhegalov.course.work.service.inner.ExpressionFactory;

public class ExpressionGeneratorServiceImpl implements ExpressionGeneratorService {

    @Override
    public Expression create(GeneratorSetup generatorSetup) {
        final var values = getRandomList(generatorSetup.getMin(), generatorSetup.getMax(),
                generatorSetup.getValueCnt());
        return ExpressionFactory.create(getRandomOperation(generatorSetup.getOperations()), values);
    }

    private ExpressionOperation getRandomOperation(List<ExpressionOperation> operations) {
        final var random = new Random();
        return operations.get(random.nextInt(operations.size()));
    }

    private List<Integer> getRandomList(int min, int max, int count) {
        return IntStream.range(0, count).boxed().map(unused -> getRandomValue(min, max)).collect(Collectors.toList());
    }

    private int getRandomValue(int min, int max) {
        final var random = new Random();
        final var bound = max - min;
        final var randInBound = (bound > 0) ? random.nextInt(bound) : 0;
        return min + randInBound;
    }
}
