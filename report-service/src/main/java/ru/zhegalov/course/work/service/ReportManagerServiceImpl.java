package ru.zhegalov.course.work.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.JasperPrint;
import ru.zhegalov.course.work.domain.Report;
import ru.zhegalov.course.work.domain.dto.ReportDto;
import ru.zhegalov.course.work.dto.ReportItemDto;
import ru.zhegalov.course.work.repository.ReportRepository;

@Service
@RequiredArgsConstructor
public class ReportManagerServiceImpl implements ReportManagerService {
    private final ReportService<JasperPrint, List<ReportItemDto>> reportService;
    private final ReportRepository reportHolderService;

    @Override
    public ReportDto prepareReport(List<ReportItemDto> data) {
        final var report = reportService.createReport(data);
        final var printedReport = reportService.print(report);
        final var reportUuid = reportHolderService.saveReport(new Report(printedReport));
        return new ReportDto(reportUuid.toString());
    }
}
