package ru.zhegalov.course.work.model.gamesettings;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.zhegalov.course.work.model.GameSettings;

@NoArgsConstructor
@Getter
@Setter
public class CalculatingGameSettings extends GameSettings {
    private Integer min;
    private Integer max;
    private Integer valueCnt;
    private List<CalculationOperation> operations;
}

