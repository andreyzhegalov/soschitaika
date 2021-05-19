package zhegalov.course.work.service;

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
import zhegalov.course.work.configuration.ByteArrayWrapper;
import zhegalov.course.work.controllers.dto.ReportItemDto;

@SuppressWarnings("rawtypes")
@RequiredArgsConstructor
@Service
public class ReportServiceJasperReports implements ReportService<JasperPrint, List<ReportItemDto>> {
    private final JasperReport jasperReport;
    private final Exporter exporter;
    private final ByteArrayWrapper byteArrayWrapper;

    @Override
    @SuppressWarnings("unchecked")
    public JasperPrint createReport(List<ReportItemDto> data) {

        final var ds = new JRBeanCollectionDataSource(data);

        final var params = new HashMap();
        params.put("datasource", ds);
        params.put("result", makeResult(data));

        try {
            return JasperFillManager.fillReport(jasperReport, params, new JREmptyDataSource());
        } catch (JRException e) {
            throw new ReportServiceException(e);
        }
    }

    private String makeResult(List<ReportItemDto> data) {
        final var correctAnswer = data.stream().filter(ReportItemDto::isCorrect).count();
        final var total = data.size();
        return String.format("Correct %d of %d", correctAnswer, total);
    }

    @Override
    @SuppressWarnings("unchecked")
    public byte[] print(JasperPrint report) {
        exporter.setExporterInput(new SimpleExporterInput(report));
        try {
            exporter.exportReport();
        } catch (JRException e) {
            throw new ReportServiceException(e);
        }
        return byteArrayWrapper.getOutputStream().toByteArray();
    }
}
