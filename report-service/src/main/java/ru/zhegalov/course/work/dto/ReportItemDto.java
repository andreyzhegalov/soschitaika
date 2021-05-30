package ru.zhegalov.course.work.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ReportItemDto {
    private String question;
    private String answer;
    private String correctAnswer;
    private boolean correct;
}

