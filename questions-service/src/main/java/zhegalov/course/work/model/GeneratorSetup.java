package zhegalov.course.work.model;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class GeneratorSetup {
    private Integer min;
    private Integer max;
    private List<ExpressionOperation> operations;

    public void setOperations(ExpressionOperation ... operations){
        this.operations = List.of(operations);
    }
}

