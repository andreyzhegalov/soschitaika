package zhegalov.course.work.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.JasperPrint;
import zhegalov.course.work.controllers.dto.ReportItemDto;
import zhegalov.course.work.service.ReportService;

@RequiredArgsConstructor
@RestController
public class ReportController {
    private final ReportService<JasperPrint, List<ReportItemDto>> reportService;

    @PostMapping(path = "/api/reports")
    @ResponseStatus(HttpStatus.CREATED)
    public String createReport(@RequestBody List<ReportItemDto> question) {
        final var report = reportService.createReport(question);
        return reportService.print(report).toString();
    }

}
