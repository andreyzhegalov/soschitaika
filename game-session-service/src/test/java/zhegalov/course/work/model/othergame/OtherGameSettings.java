package zhegalov.course.work.model.othergame;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import zhegalov.course.work.model.GameSettings;

@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class OtherGameSettings extends GameSettings {
    private String someSettings;
}
