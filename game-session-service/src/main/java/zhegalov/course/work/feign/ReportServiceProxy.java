package zhegalov.course.work.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import zhegalov.course.work.feign.dto.ReportItemDto;

@FeignClient(name = "report-service", url = "http://localhost:8032")
public interface ReportServiceProxy {

    @PostMapping(path = "/api/reports")
    @ResponseStatus(HttpStatus.CREATED)
    public byte[] createReport(@RequestBody List<ReportItemDto> question);

}
