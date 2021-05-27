package zhegalov.course.work.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.amqp.dsl.Amqp;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.Transformers;
import org.springframework.messaging.handler.annotation.SendTo;

@Configuration
public class IntegrationConfig {

    @Bean
    @SendTo("downstream.results")
    public IntegrationFlow reportCreateFlow(ConnectionFactory connectionFactory) {
        // @formatter:on
        return IntegrationFlows.from(Amqp.inboundGateway(connectionFactory, "downstream.request"))
                .log()
                .handle("reportManagerServiceImpl", "prepareReport")
                .transform(Transformers.toJson())
                .get();
        // @formatter:off
    }

    @Bean
    public MessageConverter jsonMessageConverter(ObjectMapper objectMapper) {
        return new Jackson2JsonMessageConverter(objectMapper);
    }
}
