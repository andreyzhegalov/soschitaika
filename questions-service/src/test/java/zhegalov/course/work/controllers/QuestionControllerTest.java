package zhegalov.course.work.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = QuestionController.class)
public class QuestionControllerTest {
    @Autowired
    private MockMvc mvc;

    @Test
    void shouldCreateNewQuestion() throws Exception{
        mvc.perform( post("/api/questions"))
                .andDo(print())
                .andExpect(status()
                .isCreated());
    }
}

