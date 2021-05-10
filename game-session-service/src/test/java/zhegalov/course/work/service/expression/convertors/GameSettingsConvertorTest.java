package zhegalov.course.work.service.expression.convertors;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;

import zhegalov.course.work.feign.dto.ExpressionOperation;
import zhegalov.course.work.model.gamesettings.ExpressionGameSettings;
import zhegalov.course.work.model.gamesettings.ExpressionOperationV0;

public class GameSettingsConvertorTest {
    @Test
    void shouldConvertGameSettings() {
        final var gameSettings = new ExpressionGameSettings();
        gameSettings.setMax(10);
        gameSettings.setMax(1);
        gameSettings.setValueCnt(2);
        gameSettings.setOperations(List.of(ExpressionOperationV0.MUL, ExpressionOperationV0.DIV,
                ExpressionOperationV0.SUB, ExpressionOperationV0.SUM));

        final var generatorSetup = GameSettingsConvertor.convertGameSettings(gameSettings);

        assertThat(generatorSetup).isNotNull();
        assertThat(generatorSetup.getMin()).isEqualTo(gameSettings.getMin());
        assertThat(generatorSetup.getMax()).isEqualTo(gameSettings.getMax());
        assertThat(generatorSetup.getValueCnt()).isEqualTo(gameSettings.getValueCnt());
        assertThat(generatorSetup.getOperations()).isNotEmpty();
        assertThat(generatorSetup.getOperations()).containsExactly(ExpressionOperation.MUL, ExpressionOperation.DIV,
                ExpressionOperation.SUB, ExpressionOperation.SUM);
    }
}
