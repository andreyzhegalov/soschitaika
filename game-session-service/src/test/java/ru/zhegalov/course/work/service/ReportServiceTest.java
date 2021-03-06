package ru.zhegalov.course.work.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.then;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import ru.zhegalov.course.work.dto.SessionDto;
import ru.zhegalov.course.work.service.convertors.QuestionConverter;

@SpringBootTest
public class ReportServiceTest {
    @Configuration
    @Import({ReportServiceImpl.class, QuestionConverter.class})
    public static class TestContext {
    }

    @Autowired
    private ReportService reportService;

    @MockBean
    private QuestionService questionService;

    @Test
    void shouldGetQuestionsWithAnswerFromRepositoryWhenReportDataCreated() {
        final var sessionId = "sessionId";
        final var session = new SessionDto();
        session.setSessionId(sessionId);
        reportService.createReportData(session);
        then(questionService).should().getQuestionsWithAnswer(any());
    }
}
