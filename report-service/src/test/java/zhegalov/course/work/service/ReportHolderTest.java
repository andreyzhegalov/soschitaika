package zhegalov.course.work.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.then;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import zhegalov.course.work.domain.Report;
import zhegalov.course.work.repository.ReportRepository;

@SpringBootTest
public class ReportHolderTest {

    @Configuration
    @Import(ReportHolderServiceImpl.class)
    public static class TestContext {
    }

    @Autowired
    private ReportHolderService reportHolderService;

    @MockBean
    private ReportRepository reportRepository;

    @Test
    void shouldSaveReportToRepository() {
        reportHolderService.saveReport(new Report("".getBytes()));
        then(reportRepository).should().saveReport(any());
    }

    @Test
    void shouldGetReportFromRepository() {
        final var reportId = "reportId";
        reportHolderService.getReport(reportId);
        then(reportRepository).should().getReport(reportId);
    }
}
