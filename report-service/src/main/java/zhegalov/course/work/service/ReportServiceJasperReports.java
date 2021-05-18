package zhegalov.course.work.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JsonDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import net.sf.jasperreports.export.SimplePdfReportConfiguration;

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

    @Override
    public void print(JasperPrint report) {
        final var exporter = new JRPdfExporter();

        exporter.setExporterInput(new SimpleExporterInput(report));
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput("employeeReport.pdf"));

        final var reportConfig = new SimplePdfReportConfiguration();
        reportConfig.setSizePageToContent(true);
        reportConfig.setForceLineBreakPolicy(false);

        final var exportConfig = new SimplePdfExporterConfiguration();
        exportConfig.setEncrypted(true);
        exportConfig.setAllowedPermissionsHint("PRINTING");

        exporter.setConfiguration(reportConfig);
        exporter.setConfiguration(exportConfig);
        //
        try {
            exporter.exportReport();
        } catch (JRException e) {
            throw new ReportServiceException(e);
        }
    }
}
