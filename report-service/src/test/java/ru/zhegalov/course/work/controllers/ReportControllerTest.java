package ru.zhegalov.course.work.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import net.sf.jasperreports.engine.JasperPrint;
import ru.zhegalov.course.work.dto.ReportItemDto;
import ru.zhegalov.course.work.service.ReportService;
import ru.zhegalov.course.work.TestHelper;
import ru.zhegalov.course.work.domain.Report;
import ru.zhegalov.course.work.service.ReportHolderService;

@WebMvcTest(controllers = ReportController.class)
public class ReportControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private ReportService<JasperPrint, List<ReportItemDto>> reportService;

    @MockBean
    private ReportHolderService reportHolderService;

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

    @Test
    void shouldReturnReport() throws Exception {
        final var reportId = "afa76306-f01c-4bfd-b90e-80becd0b2010";
        given(reportHolderService.getReport(reportId)).willReturn(Optional.of(new Report(new byte[0])));
        // @formatter:off
        mvc.perform(
                get("/api/reports/{reportId}", reportId)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
        // @formatter:on
        //
        then(reportHolderService).should().getReport(reportId);
    }

    @Test
    void shouldReturnNotFoundIdReportIdNotExist() throws Exception{
        given(reportHolderService.getReport(anyString())).willReturn(Optional.empty());
        // @formatter:off
        mvc.perform(
                get("/api/reports/{reportId}", "not existed report id")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());
        // @formatter:on
        //
        then(reportHolderService).should().getReport(anyString());

    }
}
