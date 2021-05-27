package zhegalov.course.work.repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import lombok.extern.slf4j.Slf4j;
import zhegalov.course.work.domain.Report;

@Slf4j
@Repository
public class ReportRepositoryHashMap implements ReportRepository {
    private final Map<UUID, Report> reportMap = new HashMap<>();

    @Override
    public String saveReport(Report report) {
        final var reportId = UUID.randomUUID();
        reportMap.put(reportId, report);
        log.info("Report with id={} saved", reportId);
        return reportId.toString();
    }

    @Override
    public Optional<Report> getReport(String reportId) {
        log.debug("Get report with id={}", reportId);
        return Optional.ofNullable(reportMap.get(UUID.fromString(reportId)));
    }

}
