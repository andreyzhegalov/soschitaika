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
    public UUID saveReport(Report report) {
        final var reportId = UUID.randomUUID();
        reportMap.put(reportId, report);
        return reportId;
    }

    @Override
    public Report getReport(UUID reportId) {
        return reportMap.getOrDefault(reportId, new Report(new byte[0]));
    }

}
