package zhegalov.course.work.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import net.sf.jasperreports.engine.JasperPrint;
import zhegalov.course.work.TestHelper;
import zhegalov.course.work.controllers.dto.ReportItemDto;
import zhegalov.course.work.service.ReportService;

@WebMvcTest(controllers = ReportController.class)
public class ReportControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private ReportService<JasperPrint, List<ReportItemDto>> reportService;

    @Test
    void shouldCreateReport() throws Exception {
        final var reportList = TestHelper.createReportItemList();
        String jsonString = new ObjectMapper().writeValueAsString(reportList);

        given(reportService.createReport(any())).willReturn(new JasperPrint());
        given(reportService.print(any())).willReturn(new byte[0]);

        mvc.perform(
                post("/api/reports").content(jsonString).header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isCreated());

        then(reportService).should().createReport(any());
        then(reportService).should().print(any());
    }

}
