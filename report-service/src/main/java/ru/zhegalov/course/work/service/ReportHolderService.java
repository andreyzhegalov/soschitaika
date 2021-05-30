package ru.zhegalov.course.work.service;

import java.util.Optional;

import ru.zhegalov.course.work.domain.Report;

public interface ReportHolderService {

    String saveReport(Report report);

    Optional<Report> getReport(String reportId);

}
