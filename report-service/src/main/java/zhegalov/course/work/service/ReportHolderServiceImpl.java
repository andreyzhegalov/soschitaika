package zhegalov.course.work.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import zhegalov.course.work.domain.Report;
import zhegalov.course.work.repository.ReportRepository;

@RequiredArgsConstructor
@Service
public class ReportHolderServiceImpl implements ReportHolderService {
    private final ReportRepository reportRepository;

    @Override
    public String saveReport(Report report) {
        return reportRepository.saveReport(report);
    }

    @Override
    public Optional<Report> getReport(String reportId) {
        return reportRepository.getReport(reportId);
    }

}
