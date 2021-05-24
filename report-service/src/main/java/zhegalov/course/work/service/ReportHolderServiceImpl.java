package zhegalov.course.work.service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Service;

import zhegalov.course.work.domain.Report;

@Service
public class ReportHolderServiceImpl implements ReportHolderService {
    private final Map<UUID, Report> reportMap = new HashMap<>();

    @Override
    public String saveReport(Report report) {
        final var reportId = UUID.randomUUID();
        reportMap.put(reportId, report);
        return reportId.toString();
    }

    @Override
    public Report getReport(String reportId) {
        return reportMap.getOrDefault(UUID.fromString(reportId), new Report(new byte[0]));
    }

}
