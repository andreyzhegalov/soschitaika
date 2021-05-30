package zhegalov.course.work.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import zhegalov.course.work.dto.ReportItemDto;
import zhegalov.course.work.dto.SessionDto;
import zhegalov.course.work.model.GameSession;
import zhegalov.course.work.model.Question;
import zhegalov.course.work.service.convertors.QuestionConverter;

@Slf4j
@RequiredArgsConstructor
@Service
public class ReportServiceImpl implements ReportService {
    private final QuestionService questionService;
    private final QuestionConverter questionConverter;

    @Override
    public List<ReportItemDto> createReportData(SessionDto sessionDto) {
        log.debug("Create report data request for {} sessionId", sessionDto.getSessionId());
        final var questionList = questionService.getQuestionsWithAnswer(convertSessionDto(sessionDto));
        return convert(questionList);
    }

    private GameSession convertSessionDto(SessionDto sessionDto) {
        final var session = new GameSession();
        session.setId(sessionDto.getSessionId());
        return session;
    }

    private List<ReportItemDto> convert(List<Question> questionList) {
        return questionList.stream().map(questionConverter::convertToReportItem).collect(Collectors.toList());
    }
}
