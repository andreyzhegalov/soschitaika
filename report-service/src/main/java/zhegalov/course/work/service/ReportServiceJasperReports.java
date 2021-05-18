package zhegalov.course.work.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.JasperReport;
import zhegalov.course.work.model.Report;

@RequiredArgsConstructor
@Service
public class ReportServiceJasperReports implements ReportService {
    private final JasperReport jasperReport;

    @Override
    public Report create() {
        return new Report();
    }
}
