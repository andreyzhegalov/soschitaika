package ru.zhegalov.course.work.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import ru.zhegalov.course.work.domain.Report;

@SpringBootTest
public class ReportRepositoryTest {

    @Configuration
    @Import(ReportRepositoryHashMap.class)
    public static class TestContext {
    }

    @Autowired
    private ReportRepository reportRepository;

    @Test
    void shouldSaveAndGetReportById(){
        final var report = new Report("report data".getBytes());
        final var reportId = reportRepository.saveReport(report);
        final var savedReport = reportRepository.getReport(reportId);
        assertThat(savedReport).isPresent().isNotNull();
        assertThat(savedReport.get().getData()).isEqualTo(report.getData());
    }
}

