package zhegalov.course.work.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.then;
import static org.mockito.BDDMockito.given;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import zhegalov.course.work.feign.ReportServiceProxy;
import zhegalov.course.work.feign.dto.ReportItemDto;
import zhegalov.course.work.model.Question;
import zhegalov.course.work.respositories.QuestionRepository;

@SpringBootTest
public class ReportServiceTest {
    @Configuration
    @Import(ReportServiceImpl.class)
    public static class TestContext {

    }

    @Autowired
    private ReportService reportService;

    @MockBean
    private QuestionRepository questionRepository;

    @MockBean
    private ReportServiceProxy reportServiceProxy;

    @Captor
    private ArgumentCaptor<List<ReportItemDto>> captor;

    @Test
    void shouldGetQuestionsWithAnswerFromRepositoryWhenReportCreated() {
        final var sessionId = "sessionId";
        reportService.createReport(sessionId);
        then(questionRepository).should().findBySessionIdAndAnswerIsNotNull(sessionId);
    }

    @Test
    void shouldGetReportFromReportServiceWhenReportCreated() {
        final var sessionId = "sessionId";
        given(questionRepository.findBySessionIdAndAnswerIsNotNull(sessionId))
                .willReturn(List.of(new Question(), new Question()));

        reportService.createReport(sessionId);

        then(reportServiceProxy).should().createReport(captor.capture());
        assertThat(captor.getValue()).isNotNull().isNotEmpty().hasOnlyElementsOfTypes(ReportItemDto.class);
    }

}
