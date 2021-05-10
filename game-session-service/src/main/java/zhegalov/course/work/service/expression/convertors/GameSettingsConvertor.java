package zhegalov.course.work.service.expression.convertors;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import zhegalov.course.work.feign.dto.ExpressionOperation;
import zhegalov.course.work.feign.dto.GeneratorSetup;
import zhegalov.course.work.model.GameSettings;
import zhegalov.course.work.model.gamesettings.ExpressionGameSettings;
import zhegalov.course.work.model.gamesettings.ExpressionOperationV0;

public class GameSettingsConvertor {
    private static final Map<ExpressionOperationV0, ExpressionOperation> OPERATION_MAP = new HashMap<>() {
        /**
         *
         */
        private static final long serialVersionUID = 1L;

        {
            put(ExpressionOperationV0.MUL, ExpressionOperation.MUL);
            put(ExpressionOperationV0.DIV, ExpressionOperation.DIV);
            put(ExpressionOperationV0.SUB, ExpressionOperation.SUB);
            put(ExpressionOperationV0.SUM, ExpressionOperation.SUM);
        }
    };

    public static GeneratorSetup convertGameSettings(GameSettings gameSettings) {
        final var settings = (ExpressionGameSettings) gameSettings;
        final var generatorSetup = new GeneratorSetup();
        generatorSetup.setMax(settings.getMax());
        generatorSetup.setMin(settings.getMin());
        generatorSetup.setValueCnt(settings.getValueCnt());
        final var operations = settings.getOperations().stream().map(OPERATION_MAP::get).collect(Collectors.toList());
        generatorSetup.setOperations(operations);
        return generatorSetup;
    }
}
