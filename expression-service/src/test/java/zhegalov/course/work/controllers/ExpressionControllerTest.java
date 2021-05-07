package zhegalov.course.work.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
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

import zhegalov.course.work.controllers.dto.ExpressionDto;
import zhegalov.course.work.model.GeneratorSetup;
import zhegalov.course.work.model.expression.ExpressionOperation;
import zhegalov.course.work.service.ExpressionGeneratorService;

@WebMvcTest(controllers = ExpressionController.class)
public class ExpressionControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private ExpressionGeneratorService expressionGeneratorService;

    @Test
    void shouldCreateNewQuestion() throws Exception{
        final var generatorSetup = new GeneratorSetup();
        generatorSetup.setMin(0);
        generatorSetup.setMax(1);
        generatorSetup.setOperations(ExpressionOperation.SUM);

        final var mapper = new ObjectMapper();
        final var jsonString = mapper.writeValueAsString(generatorSetup);

        given(expressionGeneratorService.createExpressionDto(any())).
            willReturn(new ExpressionDto());

        mvc.perform( post("/api/expressions")
                .content(jsonString)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status()
                .isCreated());

        then(expressionGeneratorService).should().createExpressionDto(any());
    }
}

