package zhegalov.course.work.model.gamesettings;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import zhegalov.course.work.model.GameSettings;

@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class ExpressionGameSettings extends GameSettings{
    private Integer min;
    private Integer max;
    private Integer valueCnt;
    private List<ExpressionOperationV0> operations;
}

