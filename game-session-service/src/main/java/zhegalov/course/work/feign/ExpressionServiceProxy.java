package zhegalov.course.work.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import zhegalov.course.work.feign.dto.ExpressionDto;
import zhegalov.course.work.feign.dto.GeneratorSetup;

@FeignClient(name = "expression-service", url = "http://localhost:8031")
public interface ExpressionServiceProxy {

    @PostMapping(path = "/api/expressions")
    @ResponseStatus(HttpStatus.CREATED)
    ExpressionDto createExpression(@RequestBody GeneratorSetup generatorSetup);

}
