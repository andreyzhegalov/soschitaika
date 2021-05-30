package ru.zhegalov.course.work.repository;

import java.util.Optional;

import ru.zhegalov.course.work.domain.Report;

public interface ReportRepository {

    String saveReport(Report report);

    Optional<Report> getReport(String reportId);

}
