package ru.zhegalov.course.work.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import net.sf.jasperreports.engine.JasperPrint;
import ru.zhegalov.course.work.dto.ReportItemDto;
import ru.zhegalov.course.work.TestHelper;

@SpringBootTest(webEnvironment = WebEnvironment.NONE)
public class ReportServiceJasperTest {

    @Autowired
    private ReportService<JasperPrint, List<ReportItemDto>> reportService;

    @Test
    void shouldPrintReport() {
        final var reportItems = TestHelper.createReportItemList();
        final var report = reportService.createReport(reportItems);

        final var byteArray = reportService.print(report);
        assertThat(byteArray).isNotNull();
    }
}
