package zhegalov.course.work.config;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.integration.core.MessagingTemplate;
import org.springframework.integration.test.context.SpringIntegrationTest;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

import zhegalov.course.work.controllers.dto.SessionDto;

@SpringBootTest
@SpringIntegrationTest
// @Import({  IntegrationConfigTests.Config.class })
// @Disabled
class IntegrationConfigTests {

	@Autowired
	@Qualifier("request.input")
	private MessageChannel requestInput;

	@Test
	public void test() {
		MessagingTemplate messagingTemplate = new MessagingTemplate();
        final var sessionDto = new SessionDto();

		Message<?> receive =
				messagingTemplate
						.sendAndReceive(requestInput,
								MessageBuilder
										.withPayload(sessionDto)
										.setHeader("Content-Type", "application/json")
										.build()
						);
		// assertThat(receive).isNotNull();
		// assertThat(receive.getPayload()).isInstanceOf(Response.class);
        //
		// Response actual = (Response) receive.getPayload();
		// assertThat(actual.getRequestId()).isEqualTo(requestId);
		// assertThat(actual.getMessage()).isEqualTo("This is message 1");

	}


}
