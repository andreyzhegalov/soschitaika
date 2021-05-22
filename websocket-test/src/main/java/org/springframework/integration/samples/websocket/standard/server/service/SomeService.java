package org.springframework.integration.samples.websocket.standard.server.service;

import org.springframework.integration.samples.websocket.standard.server.domain.Message;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SomeService {

    public Message handler(Message data) {
        log.debug("SomeService handler with {}", data);
        final var message = new Message();
        message.setMessageStr(data.getMessageStr() + " from service handler");
        sleep(5000);
        return message;
    }

    private void sleep(long ms) {
        try {
            Thread.sleep(ms);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
