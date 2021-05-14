package zhegalov.course.work.service.calculatinggame.convertors;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import zhegalov.course.work.feign.dto.ExpressionOperation;
import zhegalov.course.work.feign.dto.GeneratorSetup;
import zhegalov.course.work.model.GameSettings;
import zhegalov.course.work.model.gamesettings.CalculatingGameSettings;
import zhegalov.course.work.model.gamesettings.CalculationOperation;

public class GameSettingsConverter {
    private static final Map<CalculationOperation, ExpressionOperation> OPERATION_MAP = new HashMap<>() {
        /**
         *
         */
        private static final long serialVersionUID = 1L;

        {
            put(CalculationOperation.MUL, ExpressionOperation.MUL);
            put(CalculationOperation.DIV, ExpressionOperation.DIV);
            put(CalculationOperation.SUB, ExpressionOperation.SUB);
            put(CalculationOperation.SUM, ExpressionOperation.SUM);
        }
    };

    public static GeneratorSetup convertGameSettings(GameSettings gameSettings) {
        final var settings = (CalculatingGameSettings) gameSettings;
        final var generatorSetup = new GeneratorSetup();
        generatorSetup.setMax(settings.getMax());
        generatorSetup.setMin(settings.getMin());
        generatorSetup.setValueCnt(settings.getValueCnt());
        final var operations = settings.getOperations().stream().map(OPERATION_MAP::get).collect(Collectors.toList());
        generatorSetup.setOperations(operations);
        return generatorSetup;
    }
}
