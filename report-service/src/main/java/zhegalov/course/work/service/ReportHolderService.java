package zhegalov.course.work.service;

import java.util.UUID;

import zhegalov.course.work.domain.Report;

public interface ReportHolderService {

    UUID saveReport(Report report);

    Report getReport(UUID reportId);

}

