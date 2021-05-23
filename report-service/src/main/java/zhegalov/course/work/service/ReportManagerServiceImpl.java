package zhegalov.course.work.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.JasperPrint;
import zhegalov.course.work.controllers.dto.ReportItemDto;
import zhegalov.course.work.domain.Report;
import zhegalov.course.work.domain.dto.ReportDto;

@Service
@RequiredArgsConstructor
public class ReportManagerServiceImpl implements ReportManagerService {
    private final ReportService<JasperPrint, List<ReportItemDto>> reportService;
    private final ReportHolderService reportHolderService;

    @Override
    public ReportDto prepareReport(List<ReportItemDto> data) {
        final var report = reportService.createReport(data);
        final var reportArray = reportService.print(report);
        final var reportUuid = reportHolderService.saveReport(new Report(reportArray));
        return new ReportDto(reportUuid.toString());
    }
}
