package zhegalov.course.work.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JsonDataSource;
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
    public JasperPrint createReport(List<ReportItemDto> data) {
        final var rawJsonData = convertToJson(data);
        try {
            final var jsonDataStream = new ByteArrayInputStream(rawJsonData);
            final var jsonDataSource = new JsonDataSource(jsonDataStream);
            return JasperFillManager.fillReport(jasperReport, null, jsonDataSource);
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

    private byte[] convertToJson(List<ReportItemDto> data) {
        try {
            return new ObjectMapper().writeValueAsBytes(data);
        } catch (JsonProcessingException e) {
            throw new ReportServiceException(e);
        }
    }
}
