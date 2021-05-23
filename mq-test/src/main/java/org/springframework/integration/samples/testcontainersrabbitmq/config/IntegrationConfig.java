package org.springframework.integration.samples.testcontainersrabbitmq.config;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.amqp.dsl.Amqp;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.samples.testcontainersrabbitmq.domain.Request;
import org.springframework.integration.samples.testcontainersrabbitmq.domain.Response;

@Configuration
public class IntegrationConfig {

    @MessagingGateway(defaultRequestChannel = "request.input")
    public interface MyGateway {

        Response sendToRabbit(Request data);

    }
    @Bean
    public IntegrationFlow amqpInbound(ConnectionFactory connectionFactory) {
        return IntegrationFlows.from(Amqp.inboundAdapter(connectionFactory, "aName"))
                .handle("someService", "handleMessage")
                .channel("request.input")
                .get();
    }

    @Bean
    public IntegrationFlow request(RabbitTemplate amqpTemplate) {
        // @formatter:off
        return f -> f
            .log()
            // .transform(Transformers.toJson())
            .handle(Amqp.outboundAdapter(amqpTemplate)
            .routingKey("request")
            );
        // @formatter:on
    }
}
