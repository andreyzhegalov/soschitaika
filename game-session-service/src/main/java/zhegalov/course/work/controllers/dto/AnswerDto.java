package zhegalov.course.work.controllers.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class AnswerDto {
    private String questionId;
    private String answer;
}
