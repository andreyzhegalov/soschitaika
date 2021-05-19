package zhegalov.course.work.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import zhegalov.course.work.TestHelper;
import zhegalov.course.work.controllers.dto.ReportItemDto;

@SpringBootTest(webEnvironment = WebEnvironment.NONE)
public class ReportServiceJasperTest {

    @Autowired
    private ReportService<JasperPrint, List<ReportItemDto>> reportService;

    @Test
    void shouldPrintReport() throws JRException, IOException {
        final var reportItems = TestHelper.createReportItemList();
        final var report = reportService.createReport(reportItems);

        final var byteArray = reportService.print(report);
        assertThat(byteArray).isNotNull();


        try (final var fos = new FileOutputStream("report.pdf")) {
            fos.write(byteArray);
        }
    }
}
