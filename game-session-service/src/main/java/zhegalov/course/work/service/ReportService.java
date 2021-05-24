package zhegalov.course.work.service;

import java.util.List;

import zhegalov.course.work.dto.SessionDto;
import zhegalov.course.work.dto.ReportItemDto;

public interface ReportService {

    List<ReportItemDto> createReportData(SessionDto sessionDto);

}

