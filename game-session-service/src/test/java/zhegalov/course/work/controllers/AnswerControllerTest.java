package zhegalov.course.work.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.then;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import zhegalov.course.work.controllers.dto.AnswerDto;
import zhegalov.course.work.service.AnswerService;
import zhegalov.course.work.service.GameServiceException;

@WebMvcTest(controllers = AnswerController.class)
public class AnswerControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private AnswerService answerService;

    @Test
    void shouldSaveNewAnswerAndReturnStatusCreate() throws Exception {
        final var answerDto = new AnswerDto();
        final var mapper = new ObjectMapper();
        final var jsonString = mapper.writeValueAsString(answerDto);

        mvc.perform(
                post("/api/answers").content(jsonString).header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isCreated());

        then(answerService).should().saveNewAnswer(any());
    }

    @Test
    void shouldReturnStatusNotCreatedIfQuestionOfAnswerNotExisted() throws Exception{
        final var answerDto = new AnswerDto();
        final var mapper = new ObjectMapper();
        final var jsonString = mapper.writeValueAsString(answerDto);
        given(answerService.saveNewAnswer(any())).willThrow(GameServiceException.class);

        mvc.perform(
                post("/api/answers").content(jsonString).header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isNotFound());

        then(answerService).should().saveNewAnswer(any());
    }

}
