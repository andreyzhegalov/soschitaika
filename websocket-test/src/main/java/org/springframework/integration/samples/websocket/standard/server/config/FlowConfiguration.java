package org.springframework.integration.samples.websocket.standard.server.config;

import java.util.Optional;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.websocket.IntegrationWebSocketContainer;
import org.springframework.integration.websocket.ServerWebSocketContainer;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

@Configuration
public class FlowConfiguration {

    @MessagingGateway
    @Controller
    public interface WebSocketGateway {

        @MessageMapping("/greeting")
        @SendToUser("/queue/answer")
        @Gateway(requestChannel = "flow.input")
        String greeting(String payload);

    }

    @Bean
    public IntegrationWebSocketContainer serverWebSocketContainer() {
        return new ServerWebSocketContainer("/endpoint").withSockJs();
    }

    @Bean
    public IntegrationFlow flow() {
        // @formatter:off
        return f->f.channel(c->c.queue(10))
            .bridge(e -> e.poller(Pollers.fixedDelay(100).maxMessagesPerPoll(1)))
            .handle(System.out::println);
        // @formatter:on
    }
}
