package zhegalov.course.work.repository;

import java.util.Optional;

import zhegalov.course.work.domain.Report;

public interface ReportRepository {

    String saveReport(Report report);

    Optional<Report> getReport(String reportId);

}
