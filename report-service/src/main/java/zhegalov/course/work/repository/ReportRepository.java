package zhegalov.course.work.repository;

import zhegalov.course.work.domain.Report;

public interface ReportRepository {

    String saveReport(Report report);

    Report getReport(String reportId);

}
