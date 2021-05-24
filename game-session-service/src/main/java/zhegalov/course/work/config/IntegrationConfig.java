package zhegalov.course.work.config;

import javax.swing.TransferHandler.TransferSupport;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.bouncycastle.util.Strings;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.core.TopicExchange;
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
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.support.GenericMessage;
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

    // @Bean
    // public IntegrationFlow flow() {
    //     return f -> f
    //     .handle("someService", "handler")
    //     .channel("reportChannel");
    // }

    @Bean
    public IntegrationFlow prepareDataFlow() {
        // @formatter:on
        return f -> f
            .log()
            .handle("reportServiceImpl", "createReportData")
            // .transform(Transformers.toJson())
            .channel("requestReportFlow.input");
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
            // .transform(m -> {
            //     final var mapper = new ObjectMapper();
            //     try {
            //         return mapper.readValue((String)m, ReportDto.class);
            //     } catch (Exception e) {
            //         throw new RuntimeException(e);
            //     }
            // });
        // @formatter:on
    }

	@Bean
	public MessageConverter jsonMessageConverter(ObjectMapper objectMapper) {
		return new Jackson2JsonMessageConverter(objectMapper);
	}


		public static final String TOPIC_EXCHANGE = "downstream";

		public static final String RESULTS_QUEUE = "downstream.results";

		public static final String RESULTS_ROUTING_KEY = "downstream.results.#";

		@Bean
		TopicExchange topicExchange() {

			return ExchangeBuilder
					.topicExchange(TOPIC_EXCHANGE)
					.build();
		}

		@Bean
		Queue resultsQueue() {

			return QueueBuilder
					.nonDurable(RESULTS_QUEUE)
					.build();
		}

		@Bean
		Binding resultsBinding(TopicExchange topicExchange, Queue resultsQueue) {

			return BindingBuilder.bind(resultsQueue)
					.to(topicExchange)
					.with(RESULTS_ROUTING_KEY);
		}


    // @Bean
    // public IntegrationFlow responseReportFlow(ConnectionFactory connectionFactory,  SimpMessagingTemplate simpMessagingTemplate) {
    //     // @formatter:on
    //     return IntegrationFlows
    //         .from(Amqp.inboundAdapter(connectionFactory, "resultReport"))
    //         .log()
    //         .handle("wsReply", "reply" )
    //         .get();
    //     // @formatter:off
    // }

}
