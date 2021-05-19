package zhegalov.course.work.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import zhegalov.course.work.feign.ReportServiceProxy;
import zhegalov.course.work.feign.dto.ReportItemDto;
import zhegalov.course.work.model.Question;
import zhegalov.course.work.respositories.QuestionRepository;
import zhegalov.course.work.service.convertors.QuestionConverter;

@RequiredArgsConstructor
@Service
public class ReportServiceImpl implements ReportService {
    private final QuestionRepository questionRepository;
    private final ReportServiceProxy reportService;

    @Override
    public byte[] createReport(String sessionId) {
        final var questions = questionRepository.findBySessionIdAndAnswerIsNotNull(sessionId);
        final var reportItems = convert(questions);
        return reportService.createReport(reportItems);
    }

    private List<ReportItemDto> convert(List<Question> questionList) {
        return questionList.stream().map(QuestionConverter::convertToReportItem).collect(Collectors.toList());
    }
}
