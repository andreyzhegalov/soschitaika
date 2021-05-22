package org.springframework.integration.samples.websocket.standard.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.core.MessageSource;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.websocket.IntegrationWebSocketContainer;
import org.springframework.integration.websocket.ServerWebSocketContainer;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Controller;

@Configuration
public class FlowConfiguration {

    @MessagingGateway
    @Controller
    public interface WebSocketGateway {

        @MessageMapping("/message")
        @SendToUser("/queue/answer")
        @Gateway(requestChannel = "flow.input")
        String greeting(String payload);

    }

    // @Bean
    // public IntegrationWebSocketContainer serverWebSocketContainer() {
    //     return new ServerWebSocketContainer("/endpoint")
    //         .withSockJs();
    // }
    //
    // @Bean
    // @InboundChannelAdapter(value = "flow.input", poller = @Poller(fixedDelay = "1000", maxMessagesPerPoll = "1"))
    // public MessageSource<?> webSocketSessionsMessageSource() {
    //     return () -> new GenericMessage<>(serverWebSocketContainer().getSessions().keySet().iterator());
    // }

    @Bean
    public IntegrationFlow flow() {
        return f -> f.handle(p -> {
            System.out.println("!!!!!!!!!!!!!");
            System.out.println(p.getPayload());
        });
        // return f->f.channel("inputChanel")
        // .handle(p -> {
        // System.out.println("!!!!!!!!!!!!!");
        // });
        // @formatter:off
        // return f->f.channel(c->c.queue(10))
        //     .bridge(e -> e.poller(Pollers.fixedDelay(1000).maxMessagesPerPoll(1)))
        //     .handle(p -> {
        //         System.out.println("!!!!!!!!!!!!!" + p);
        //     });
        // @formatter:on
    }
}
