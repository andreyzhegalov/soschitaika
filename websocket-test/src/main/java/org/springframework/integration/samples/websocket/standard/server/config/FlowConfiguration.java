package org.springframework.integration.samples.websocket.standard.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

@Configuration
public class FlowConfiguration {

    @MessagingGateway
    @Controller
    public interface WebSocketGateway {

        @MessageMapping("/reports")
        @SendToUser("/queue/answer")
        @Gateway(requestChannel = "flow.input")
        String getReport(String payload);

    }

    @Bean
    public IntegrationFlow flow() {
        return f -> f.handle(p -> {
            System.out.println("!!!!!!!!!!!!!");
            System.out.println(p.getPayload());
        });
    }
}
