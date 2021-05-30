package ru.zhegalov.course.work.dto;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class GeneratorSetup {
    private Integer min;
    private Integer max;
    private Integer valueCnt = 2;
    private List<ExpressionOperation> operations;
}
