package zhegalov.course.work.feign.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ExpressionDto {
    private String expression;
    private Integer result;
}

