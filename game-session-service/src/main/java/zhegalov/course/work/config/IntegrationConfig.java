package zhegalov.course.work.config;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.amqp.dsl.Amqp;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.Transformers;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import zhegalov.course.work.controllers.dto.SessionDto;

@Configuration
public class IntegrationConfig {

    @MessagingGateway
    @Controller
    public interface WebSocketGateway {

        @MessageMapping("/reports")
        @SendTo("/topic/response")
        @Gateway(requestChannel = "prepareDatFlow.input", replyChannel = "reportChannel")
        String createReport(@RequestBody SessionDto session);

    }

    @Bean
    public IntegrationFlow prepareDatFlow() {
        // @formatter:on
        return f -> f
            .log()
            .handle("reportServiceImpl", "createReportData")
            .channel("requestReportFlow.input");
        // @formatter:off
    }

    @Bean
    public IntegrationFlow requestReportFlow(RabbitTemplate amqpTemplate) {
        // @formatter:off
        return f -> f
            .log()
            .transform(Transformers.toJson())
            .handle(Amqp.outboundAdapter(amqpTemplate)
            .routingKey("dataForReport")
            );
        // @formatter:on
    }

    @Bean
    public IntegrationFlow responseReportFlow(ConnectionFactory connectionFactory) {
        // @formatter:on
        return IntegrationFlows.from(Amqp.inboundAdapter(connectionFactory, "resultReport"))
                .channel("reportChannel")
                .get();
        // @formatter:off
    }

}
