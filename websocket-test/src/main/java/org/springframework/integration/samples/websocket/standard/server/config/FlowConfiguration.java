package org.springframework.integration.samples.websocket.standard.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.samples.websocket.standard.server.domain.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Configuration
public class FlowConfiguration {

    @MessagingGateway
    @Controller
    public interface WebSocketGateway {

        @MessageMapping("/reports")
        @SendTo("/topic/response")
        @Gateway(requestChannel = "flow.input", replyChannel = "reportChannel")
        Message getReport(Message payload);

    }

    @Bean
    public IntegrationFlow flow() {
        return f -> f
        .handle("someService", "handler")
        .channel("reportChannel");
    }
}
