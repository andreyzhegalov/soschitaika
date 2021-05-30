package ru.zhegalov.course.work.service;

import java.util.List;

import ru.zhegalov.course.work.domain.dto.ReportDto;
import ru.zhegalov.course.work.dto.ReportItemDto;

public interface ReportManagerService {

    ReportDto prepareReport(List<ReportItemDto> data);

}
