package zhegalov.course.work.service;

import java.util.List;

import zhegalov.course.work.domain.dto.ReportDto;
import zhegalov.course.work.dto.ReportItemDto;

public interface ReportManagerService {

    ReportDto prepareReport(List<ReportItemDto> data);

}
