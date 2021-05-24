package zhegalov.course.work.service;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.JasperPrint;
import zhegalov.course.work.controllers.dto.ReportItemDto;
import zhegalov.course.work.domain.Report;
import zhegalov.course.work.domain.dto.ReportDto;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReportManagerServiceImpl implements ReportManagerService {
    private final ReportService<JasperPrint, List<ReportItemDto>> reportService;
    private final ReportHolderService reportHolderService;

    @Override
    public ReportDto prepareReport(List<ReportItemDto> data) {
        final var report = reportService.createReport(data);
        final var reportArray = reportService.print(report);
        final var reportUuid = reportHolderService.saveReport(new Report(reportArray));
        return new ReportDto(reportUuid.toString());
    }

    public ReportDto prepareReportFake(List<ReportItemDto> data) {
        log.debug("!!!!!!!!!!!!!! SomeService handler with {}", data);
        return new ReportDto("some report id");
    }
    //
    // @RabbitListener(
    //         bindings = @QueueBinding(
    //                 value = @Queue(value = "downstream.request", durable = "true"),
    //                 exchange = @Exchange(value = "downstream", ignoreDeclarationExceptions = "true", type = "topic"),
    //                 key = "downstream.request.#"
    //         )
    // )
    // @SendTo("downstream.results")
    public String handler(String data) throws JsonProcessingException {
        final var mapper = new ObjectMapper();
        // final var sessionDto = mapper.readValue(data, SessionDto.class);

        log.debug("!!!!!!!!!!!!!! SomeService handler with {}", data);
        final var message = new ReportDto();
        message.setReportId(" from service handler");
        // sleep(3000);
        return mapper.writeValueAsString(message);
	}
}
