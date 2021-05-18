package zhegalov.course.work.service;

import org.springframework.stereotype.Service;

import zhegalov.course.work.model.Report;

@Service
public class ReportServiceJasperReports implements ReportService {

    @Override
    public Report create() {
        return new Report();
    }

}
