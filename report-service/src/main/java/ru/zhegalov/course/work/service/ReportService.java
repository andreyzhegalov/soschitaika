package ru.zhegalov.course.work.service;

public interface ReportService<R, T> {

    R createReport(T data);

    byte[] print(R report);

}
