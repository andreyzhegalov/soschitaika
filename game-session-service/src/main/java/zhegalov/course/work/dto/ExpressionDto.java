package zhegalov.course.work.dto;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ExpressionDto {
    private String operation;
    private List<Integer> values;
    private String expression;
    private Integer result;
}

