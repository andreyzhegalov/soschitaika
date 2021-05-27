package zhegalov.course.work.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Document("gameSession")
public class GameSession {
    @Id
    private String id;

    private Integer questionCount;

    private GameSettings gameSettings;
}

