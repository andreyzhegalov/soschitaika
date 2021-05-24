package zhegalov.course.work.service;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import zhegalov.course.work.domain.Report;

@SpringBootTest
public class ReportHolderTest {

    @Configuration
    @Import(ReportHolderServiceImpl.class)
    public static class TestContext {
    }

    @Autowired
    private ReportHolderService reportHolderService;

    @Test
    void shouldSaveAndGetReportById(){
        final var report = new Report("report data".getBytes());
        final var reportId = reportHolderService.saveReport(report);
        final var savedReport = reportHolderService.getReport(reportId);
        assertThat(savedReport).isSameAs(report);
    }
}
