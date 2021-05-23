package org.springframework.integration.samples.testcontainersrabbitmq.domain;

import java.util.UUID;

public class Request {

	private final UUID id;

	private final Integer messageId;

	public Request(UUID id, Integer messageId) {
		this.id = id;
		this.messageId = messageId;
	}

	public UUID getId() {
		return this.id;
	}

	public Integer getMessageId() {
		return this.messageId;
	}

	@Override
	public String toString() {
		return "Request{" +
				"id=" + this.id +
				", messageId=" + this.messageId +
				'}';
	}

}
