package zhegalov.course.work.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.JasperPrint;
import zhegalov.course.work.controllers.dto.ReportItemDto;
import zhegalov.course.work.service.ReportService;

@RequiredArgsConstructor
@RestController
@Slf4j
public class ReportController {
    private final ReportService<JasperPrint, List<ReportItemDto>> reportService;

    @PostMapping(path = "/api/reports")
    @ResponseStatus(HttpStatus.CREATED)
    public byte[] createReport(@RequestBody List<ReportItemDto> questions) {
        log.debug("Received: \n {}", questions);
        final var report = reportService.createReport(questions);
        return reportService.print(report);
    }

}
