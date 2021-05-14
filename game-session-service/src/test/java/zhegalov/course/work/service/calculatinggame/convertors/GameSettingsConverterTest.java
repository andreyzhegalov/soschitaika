package zhegalov.course.work.service.calculatinggame.convertors;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;

import zhegalov.course.work.feign.dto.ExpressionOperation;
import zhegalov.course.work.model.gamesettings.CalculatingGameSettings;
import zhegalov.course.work.model.gamesettings.CalculationOperation;

public class GameSettingsConverterTest {
    @Test
    void shouldConvertGameSettings() {
        final var gameSettings = new CalculatingGameSettings();
        gameSettings.setMax(10);
        gameSettings.setMax(1);
        gameSettings.setValueCnt(2);
        gameSettings.setOperations(List.of(CalculationOperation.MUL, CalculationOperation.DIV,
                CalculationOperation.SUB, CalculationOperation.SUM));

        final var generatorSetup = GameSettingsConverter.convertGameSettings(gameSettings);

        assertThat(generatorSetup).isNotNull();
        assertThat(generatorSetup.getMin()).isEqualTo(gameSettings.getMin());
        assertThat(generatorSetup.getMax()).isEqualTo(gameSettings.getMax());
        assertThat(generatorSetup.getValueCnt()).isEqualTo(gameSettings.getValueCnt());
        assertThat(generatorSetup.getOperations()).isNotEmpty();
        assertThat(generatorSetup.getOperations()).containsExactly(ExpressionOperation.MUL, ExpressionOperation.DIV,
                ExpressionOperation.SUB, ExpressionOperation.SUM);
    }
}
