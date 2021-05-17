package zhegalov.course.work.controllers.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ExpressionDto {
    private String expression;
    private Integer result;
}

