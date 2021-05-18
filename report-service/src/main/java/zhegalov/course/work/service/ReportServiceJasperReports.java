package zhegalov.course.work.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JsonDataSource;

@RequiredArgsConstructor
@Service
public class ReportServiceJasperReports implements ReportService<JasperPrint, JsonDataSource> {
    private final JasperReport jasperReport;

    @Override
    public JasperPrint createReport(JsonDataSource data) {
        try {
            return JasperFillManager.fillReport(jasperReport, null, data);
        } catch (JRException e) {
            throw new ReportServiceException(e);
        }
    }
}
