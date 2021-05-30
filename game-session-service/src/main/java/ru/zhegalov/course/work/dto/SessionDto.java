package ru.zhegalov.course.work.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.zhegalov.course.work.model.GameSession;

@Getter
@Setter
@NoArgsConstructor
public class SessionDto {
    private String sessionId;

    public SessionDto(GameSession session){
        this.sessionId = session.getId();
    }
}

