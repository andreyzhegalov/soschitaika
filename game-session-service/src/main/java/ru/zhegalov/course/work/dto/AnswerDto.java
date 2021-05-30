package ru.zhegalov.course.work.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class AnswerDto {
    private String questionId;
    private String answer;
}
