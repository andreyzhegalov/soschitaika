package zhegalov.course.work.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import zhegalov.course.work.controllers.dto.SessionDto;
import zhegalov.course.work.dto.ReportDto;

@Slf4j
// @Service
public class SomeServiceReceiver {

	// @RabbitListener(
	//         bindings = @QueueBinding(
	//                 value = @Queue(value = "downstream.request", durable = "true"),
	//                 exchange = @Exchange(value = "downstream", ignoreDeclarationExceptions = "true", type = "topic"),
	//                 key = "downstream.request.#"
	//         )
	// )
	// @SendTo("downstream.results")
    public ReportDto handler(String data) throws JsonProcessingException {
        final var mapper = new ObjectMapper();
        final var sessionDto = mapper.readValue(data, SessionDto.class);

        log.debug("!!!!!!!!!!!!!! SomeService handler with {}", data);
        final var message = new ReportDto();
        message.setReportId(sessionDto.getSessionId() + " from service handler");
        sleep(3000);
        return message;
	}

    private void sleep(long ms) {
        try {
            Thread.sleep(ms);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

