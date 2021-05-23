package zhegalov.course.work.configuration;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.amqp.dsl.Amqp;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.Transformers;

import zhegalov.course.work.controllers.dto.ReportItemDto;

@Configuration
public class IntegrationConfig {

    @Bean
    public IntegrationFlow requestFlow(ConnectionFactory connectionFactory) {
        // @formatter:on
        return IntegrationFlows.from(Amqp.inboundAdapter(connectionFactory, "dataForReport"))
                .log()
                .transform(Transformers.fromJson(ReportItemDto[].class))
                .handle("reportManagerServiceImpl", "prepareReport")
                .channel("responseFlow.input")
                .get();
        // @formatter:off
    }

    @Bean
    public IntegrationFlow responseFlow(RabbitTemplate amqpTemplate) {
        // @formatter:off
        return f -> f
            .log()
            .transform(Transformers.toJson())
            .handle(Amqp.outboundAdapter(amqpTemplate)
            .routingKey("resultReport")
            );
        // @formatter:on
    }
}
