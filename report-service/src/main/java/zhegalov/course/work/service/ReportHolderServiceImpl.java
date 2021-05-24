package zhegalov.course.work.service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import zhegalov.course.work.domain.Report;

@Slf4j
@Service
public class ReportHolderServiceImpl implements ReportHolderService {
    private final Map<UUID, Report> reportMap = new HashMap<>();

    @Override
    public String saveReport(Report report) {
        final var reportId = UUID.randomUUID();
        reportMap.put(reportId, report);
        log.info("Report with id={} saved", reportId);
        return reportId.toString();
    }

    @Override
    public Report getReport(String reportId) {
        log.debug("Get report with id={}", reportId);
        return reportMap.get(UUID.fromString(reportId));
    }

}
