package ru.zhegalov.course.work.service;

import java.util.List;

import ru.zhegalov.course.work.dto.SessionDto;
import ru.zhegalov.course.work.dto.ReportItemDto;

public interface ReportService {

    List<ReportItemDto> createReportData(SessionDto sessionDto);

}

