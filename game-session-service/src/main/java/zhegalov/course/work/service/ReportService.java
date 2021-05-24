package zhegalov.course.work.service;

import java.util.List;

import zhegalov.course.work.controllers.dto.SessionDto;
import zhegalov.course.work.feign.dto.ReportItemDto;

public interface ReportService {

    List<ReportItemDto> createReportData(SessionDto sessionDto);

}

