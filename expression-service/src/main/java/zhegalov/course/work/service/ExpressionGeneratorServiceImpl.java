package zhegalov.course.work.service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import zhegalov.course.work.dto.ExpressionDto;
import zhegalov.course.work.model.GeneratorSetup;
import zhegalov.course.work.model.expression.Expression;
import zhegalov.course.work.model.expression.ExpressionOperation;
import zhegalov.course.work.service.inner.ExpressionFactory;

@RequiredArgsConstructor
@Service
public class ExpressionGeneratorServiceImpl implements ExpressionGeneratorService {
    private final ExpressionPrintService printService;

    @Override
    public Expression create(GeneratorSetup generatorSetup) {
        final var values = getRandomList(generatorSetup.getMin(), generatorSetup.getMax(),
                generatorSetup.getValueCnt());
        return ExpressionFactory.create(getRandomOperation(generatorSetup.getOperations()), values);
    }

    @Override
    public ExpressionDto createExpressionDto(GeneratorSetup generatorSetup) {
        final var expression = create(generatorSetup);
        return convert(expression);
    }

    private ExpressionDto convert(Expression expression) {
        final var expressionDto = new ExpressionDto();
        expressionDto.setExpression(printService.print(expression));
        expressionDto.setResult(expression.getResult());
        expressionDto.setOperation(expression.getOperation());
        expressionDto.setValues(expression.getValues());
        return expressionDto;
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
