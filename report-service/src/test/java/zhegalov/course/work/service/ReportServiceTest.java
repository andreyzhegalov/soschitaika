package zhegalov.course.work.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.ByteArrayInputStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JsonDataSource;

@SpringBootTest(webEnvironment = WebEnvironment.NONE)
public class ReportServiceTest {

    @Autowired
    private ReportService<JasperPrint, JsonDataSource> reportService;

    @Test
    void shouldCreateReport() throws JRException{
        final var rawJsonData = "{}";
        final var jsonDataStream = new ByteArrayInputStream(rawJsonData.getBytes());
        final var jsonDataSource = new JsonDataSource(jsonDataStream);
        assertThat( reportService.createReport(jsonDataSource) ).isNotNull();
    }

    @Test
    void shouldPrintReport() throws JRException{
        final var rawJsonData = "{}";
        final var jsonDataStream = new ByteArrayInputStream(rawJsonData.getBytes());
        final var jsonDataSource = new JsonDataSource(jsonDataStream);
        final var report = reportService.createReport(jsonDataSource) ;
        reportService.print(report);
    }
}

