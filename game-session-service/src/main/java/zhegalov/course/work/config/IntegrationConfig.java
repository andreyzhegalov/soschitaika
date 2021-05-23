package zhegalov.course.work.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.dsl.IntegrationFlow;
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

        @MessageMapping("/api/reports")
        @SendTo("/topic/response")
        @Gateway(requestChannel = "flow.input", replyChannel = "reportChannel")
        String createReport(@RequestBody SessionDto session);

    }

    @Bean
    public IntegrationFlow flow() {
        return f -> f.handle("someService", "handler").channel("reportChannel");
    }

}
