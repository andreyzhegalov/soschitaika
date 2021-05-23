package org.springframework.integration.samples.testcontainersrabbitmq;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.integration.samples.testcontainersrabbitmq.config.IntegrationConfig.MyGateway;
import org.springframework.integration.samples.testcontainersrabbitmq.domain.Request;

@SpringBootTest
public class IntegrationConfigTest {
    @Autowired
    private MyGateway gateway;

    @Test
    void shouldSendToQueue(){
        final var request = new Request(UUID.randomUUID(), 123);
        gateway.sendToRabbit(request);
    }

}

