package zhegalov.course.work.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JsonDataSource;
import net.sf.jasperreports.export.Exporter;
import net.sf.jasperreports.export.SimpleExporterInput;

@SuppressWarnings("rawtypes")
@RequiredArgsConstructor
@Service
public class ReportServiceJasperReports implements ReportService<JasperPrint, JsonDataSource> {
    private final JasperReport jasperReport;
    private final Exporter exporter;

    @Override
    public JasperPrint createReport(JsonDataSource data) {
        try {
            return JasperFillManager.fillReport(jasperReport, null, data);
        } catch (JRException e) {
            throw new ReportServiceException(e);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public void print(JasperPrint report) {
        exporter.setExporterInput(new SimpleExporterInput(report));
        try {
            exporter.exportReport();
        } catch (JRException e) {
            throw new ReportServiceException(e);
        }
    }
}
