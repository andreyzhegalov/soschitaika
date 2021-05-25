package zhegalov.course.work.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import zhegalov.course.work.model.GameSession;

@Data
@NoArgsConstructor
public class SessionDto {
    private String sessionId;

    public SessionDto(GameSession session){
        this.sessionId = session.getId();
    }
}

