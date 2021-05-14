package zhegalov.course.work.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Document("gameSession")
public class GameSession {
    @Id
    private String id;

    private Integer questionCount;

    private GameSettings gameSettings;
}

