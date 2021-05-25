package zhegalov.course.work.service;

import java.util.List;

import zhegalov.course.work.controllers.dto.ReportItemDto;
import zhegalov.course.work.domain.dto.ReportDto;

public interface ReportManagerService {

    ReportDto prepareReport(List<ReportItemDto> data);

}

