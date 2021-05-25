package zhegalov.course.work.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;

@Document("question")
@NoArgsConstructor
@Data
public class Question {
    @Id
    private String id;
    private String sessionId;
    private String text;
    private QuestionData questionData;
    private String correctAnswer;
    private String answer;
}

