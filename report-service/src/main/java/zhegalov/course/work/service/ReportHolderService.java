package zhegalov.course.work.service;

import zhegalov.course.work.domain.Report;

public interface ReportHolderService {

    String saveReport(Report report);

    Report getReport(String reportId);

}

