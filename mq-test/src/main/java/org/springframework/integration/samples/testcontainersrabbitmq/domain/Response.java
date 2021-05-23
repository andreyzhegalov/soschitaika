package org.springframework.integration.samples.testcontainersrabbitmq.domain;

import java.util.UUID;

public class Response {

	private final UUID requestId;

	private final String message;

	public Response(UUID requestId, String message) {
		this.requestId = requestId;
		this.message = message;
	}

	public UUID getRequestId() {
		return this.requestId;
	}

	public String getMessage() {
		return this.message;
	}

	@Override
	public String toString() {
		return "Response{" +
				"requestId=" + this.requestId +
				", message='" + this.message + '\'' +
				'}';
	}

}
