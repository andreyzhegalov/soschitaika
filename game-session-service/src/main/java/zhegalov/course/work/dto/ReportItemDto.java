package zhegalov.course.work.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ReportItemDto {
    private String question;
    private String answer;
    private String correctAnswer;
    private boolean isCorrect;
}

