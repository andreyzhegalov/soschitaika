package zhegalov.course.work.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.then;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.export.Exporter;
import zhegalov.course.work.TestHelper;
import zhegalov.course.work.dto.ReportItemDto;

@SpringBootTest(webEnvironment = WebEnvironment.NONE)
@SuppressWarnings("rawtypes")
public class ReportServiceTest {

    @Autowired
    private ReportService<JasperPrint, List<ReportItemDto>> reportService;

    @MockBean
    private Exporter exporter;

    @Test
    void shouldCreateReport() {
        final var reportItems = TestHelper.createReportItemList();
        assertThat(reportService.createReport(reportItems)).isNotNull();
    }

    @Test
    void shouldPrintReport() throws JRException {
        final var reportItems = TestHelper.createReportItemList();
        final var report = reportService.createReport(reportItems);

        final var byteArray = reportService.print(report);
        assertThat(byteArray).isNotNull();

        then(exporter).should().exportReport();
    }
}
