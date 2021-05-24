package zhegalov.course.work.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import zhegalov.course.work.domain.Report;

@Service
public class ReportHolderServiceImpl implements ReportHolderService{

	@Override
	public UUID saveReport(Report report) {
        // TODO add implementation
        return UUID.randomUUID();
	}

	@Override
	public Report getReport(UUID reportId) {
		// TODO Auto-generated method stub
		return null;
	}

}

