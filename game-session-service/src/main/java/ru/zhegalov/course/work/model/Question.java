package ru.zhegalov.course.work.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document("question")
@NoArgsConstructor
@Getter
@Setter
public class Question {
    @Id
    private String id;
    private String sessionId;
    private String text;
    private QuestionData questionData;
    private String correctAnswer;
    private String answer;
}

