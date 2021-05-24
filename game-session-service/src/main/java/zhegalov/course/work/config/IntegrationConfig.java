package zhegalov.course.work.config;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.amqp.dsl.Amqp;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.Transformers;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import zhegalov.course.work.controllers.dto.SessionDto;
import zhegalov.course.work.dto.ReportDto;

@Configuration
public class IntegrationConfig {

    @MessagingGateway
    @Controller
    public interface WebSocketGateway {

        @MessageMapping("/reports")
        @SendTo("/topic/response")
        @Gateway(requestChannel = "prepareDataFlow.input", replyChannel = "reportChannel")
        ReportDto createReport(@RequestBody SessionDto session);

    }

    @Bean
    public IntegrationFlow prepareDataFlow() {
        // @formatter:on
        return f -> f.log().handle("reportServiceImpl", "createReportData").channel("requestReportFlow.input");
        // @formatter:off
    }

    @Bean
    public DirectChannel reportChannel(){
        return new DirectChannel();
    }

    @Bean
    public IntegrationFlow requestReportFlow(RabbitTemplate amqpTemplate) {
        // @formatter:off
        return f -> f
            .log()
            .handle(Amqp.outboundGateway(amqpTemplate)
						.exchangeName("downstream")
						.routingKey("downstream.request"))
            .transform(Transformers.fromJson(ReportDto.class));
        // @formatter:on
    }

    @Bean
    public MessageConverter jsonMessageConverter(ObjectMapper objectMapper) {
        return new Jackson2JsonMessageConverter(objectMapper);
    }
}
