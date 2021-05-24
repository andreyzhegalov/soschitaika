package org.springframework.integration.samples.testcontainersrabbitmq;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

@Component
public class Receiver {

	private static final Logger log = LoggerFactory.getLogger(Receiver.class);

	private static final Map<Integer, String> messages;

	static {

		messages = new HashMap<>();

		messages.put(1, "This is message 1");
		messages.put(2, "This is message 2");
		messages.put(3, "This is message 3");
		messages.put(4, "This is message 4");
		messages.put(5, "This is message 5");

	}

	@PostConstruct
	public void initialize() {
		log.info("Receiver initialized!");
	}

	@RabbitListener(
			bindings = @QueueBinding(
					value = @Queue(value = "downstream.request", durable = "true"),
					exchange = @Exchange(value = "downstream", ignoreDeclarationExceptions = "true", type = "topic"),
					key = "downstream.request.#"
			)
	)
	@SendTo("downstream.results")
	public Response handleMessage(Request request) throws InterruptedException {
		log.info("!!!!!!!!!!!!!!!!!!!! handleMessage : received message [{}]", request);

		Integer messageId;
		if (null != request.getMessageId()) {

			messageId = request.getMessageId();

		}
		else {

			messageId = new Random().ints(1, 5).findFirst().getAsInt();

		}

		return new Response(request.getId(), messages.get(messageId));
	}

}
