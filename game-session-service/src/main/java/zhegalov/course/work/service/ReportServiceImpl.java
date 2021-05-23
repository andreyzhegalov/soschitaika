package zhegalov.course.work.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import zhegalov.course.work.controllers.dto.SessionDto;
import zhegalov.course.work.feign.ReportServiceProxy;
import zhegalov.course.work.feign.dto.ReportItemDto;
import zhegalov.course.work.model.Question;
import zhegalov.course.work.respositories.QuestionRepository;
import zhegalov.course.work.service.convertors.QuestionConverter;

@Slf4j
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

    @Override
    public List<ReportItemDto> createReportData(SessionDto sessionDto) {
        log.debug("Create report data request for {} sessionId", sessionDto.getSessionId());

        final var item1 = new ReportItemDto();
        item1.setAnswer("answer1");
        item1.setQuestion("question1");
        item1.setCorrectAnswer("answer1");
        item1.setCorrect(true);

        final var item2 = new ReportItemDto();
        item2.setAnswer("answer2");
        item2.setQuestion("question2");
        item2.setCorrectAnswer("answer1");
        item2.setCorrect(false);
        return List.of(item1, item2);
    }
}
