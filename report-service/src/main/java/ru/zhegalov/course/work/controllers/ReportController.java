package ru.zhegalov.course.work.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.JasperPrint;
import ru.zhegalov.course.work.dto.ReportItemDto;
import ru.zhegalov.course.work.service.ReportService;
import ru.zhegalov.course.work.service.ReportHolderService;

@RequiredArgsConstructor
@RestController
public class ReportController {
    private final ReportService<JasperPrint, List<ReportItemDto>> reportService;
    private final ReportHolderService reportHolderService;

    @PostMapping(path = "/api/reports")
    @ResponseStatus(HttpStatus.CREATED)
    public byte[] createReport(@RequestBody List<ReportItemDto> questions) {
        final var report = reportService.createReport(questions);
        return reportService.print(report);
    }

    @GetMapping(path = "/api/reports/{reportId}")
    @ResponseStatus(HttpStatus.OK)
    public String getReport(@PathVariable("reportId") String reportId) {
        final var report = reportHolderService.getReport(reportId);
        if(report.isPresent()){
            return new String(report.get().getData());
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                "report with id " + reportId + " not found");
    }

}
