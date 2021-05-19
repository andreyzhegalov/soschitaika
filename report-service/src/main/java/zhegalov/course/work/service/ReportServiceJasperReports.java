package zhegalov.course.work.service;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.export.Exporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import zhegalov.course.work.controllers.dto.ReportItemDto;

@SuppressWarnings("rawtypes")
@RequiredArgsConstructor
@Service
public class ReportServiceJasperReports implements ReportService<JasperPrint, List<ReportItemDto>> {
    private final JasperReport jasperReport;
    private final Exporter exporter;

    @Override
    @SuppressWarnings("unchecked")
    public JasperPrint createReport(List<ReportItemDto> data) {

        final var ds = new JRBeanCollectionDataSource(data);

        final var params = new HashMap();
        params.put("datasource", ds);

        try {
            return JasperFillManager.fillReport(jasperReport, params, new JREmptyDataSource());
        } catch (JRException e) {
            throw new ReportServiceException(e);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public byte[] print(JasperPrint report) {
        final var baos = new ByteArrayOutputStream();
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(baos));
        exporter.setExporterInput(new SimpleExporterInput(report));
        try {
            exporter.exportReport();
        } catch (JRException e) {
            throw new ReportServiceException(e);
        }
        return baos.toByteArray();
    }
}
