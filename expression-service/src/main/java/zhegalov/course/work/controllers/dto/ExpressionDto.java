package zhegalov.course.work.controllers.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ExpressionDto {
    private String operation;
    private List<Integer> values = new ArrayList<>();
    private String expression;
    private Integer result;
}

