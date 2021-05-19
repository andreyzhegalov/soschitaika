package zhegalov.course.work.controllers;

import org.bouncycastle.util.Strings;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import zhegalov.course.work.controllers.dto.SessionDto;
import zhegalov.course.work.service.ReportService;

@RequiredArgsConstructor
@RestController
public class ReportController {
    private final ReportService reportService;

    @PostMapping("/api/reports")
    @ResponseStatus(HttpStatus.CREATED)
    public String createReport(@RequestBody SessionDto session) {
        final var ba = reportService.createReport(session.getSessionId());
        return Strings.fromByteArray(ba);
    }

}