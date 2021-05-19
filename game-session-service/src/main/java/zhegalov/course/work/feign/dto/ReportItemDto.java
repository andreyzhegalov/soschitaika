package zhegalov.course.work.feign.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ReportItemDto {
    private String question;
    private String answer;
    private String correctAnswer;
    private boolean isCorrect;
}

