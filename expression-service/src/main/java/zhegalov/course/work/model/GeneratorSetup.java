package zhegalov.course.work.model;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import zhegalov.course.work.model.expression.ExpressionOperation;

@Setter
@Getter
@NoArgsConstructor
public class GeneratorSetup {
    private Integer min;
    private Integer max;
    private Integer valueCnt = 2;
    private List<ExpressionOperation> operations;

    public void setOperations(ExpressionOperation ... operations){
        this.operations = List.of(operations);
    }
}

