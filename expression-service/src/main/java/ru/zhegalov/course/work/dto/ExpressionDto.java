package ru.zhegalov.course.work.dto;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ExpressionDto {
    private String operation;
    private List<Integer> values;
    private String expression;
    private Integer result;
}

