package ru.zhegalov.course.work.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.export.Exporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import ru.zhegalov.course.work.configuration.ByteArrayWrapper;
import ru.zhegalov.course.work.dto.ReportItemDto;

@SuppressWarnings("rawtypes")
@Service
public class ReportServiceJasperReports implements ReportService<JasperPrint, List<ReportItemDto>> {
    private final Exporter exporter;
    private final ByteArrayWrapper byteArrayWrapper;
    private final String reportTemplatePath;

    public ReportServiceJasperReports(Exporter exporter, ByteArrayWrapper byteArrayWrapper,
            @Value("${jasper.template}") String reportTemplatePath) throws JRException {
        this.reportTemplatePath = reportTemplatePath;
        this.exporter = exporter;
        this.byteArrayWrapper = byteArrayWrapper;
    }

    @Override
    @SuppressWarnings("unchecked")
    public JasperPrint createReport(List<ReportItemDto> data) {
        final var reportTemplate = getClass().getClassLoader().getResourceAsStream(reportTemplatePath);
        JasperReport jasperReport;
        try {
            jasperReport = JasperCompileManager.compileReport(reportTemplate);
        } catch (JRException e) {
            throw new ReportServiceException(e);
        }
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

    private String makeResult(List<ReportItemDto> data) {
        final var correctAnswer = data.stream().filter(ReportItemDto::isCorrect).count();
        final var total = data.size();
        return String.format("Correct %d of %d", correctAnswer, total);
    }
}
