package org.springframework.integration.samples.testcontainersrabbitmq.service;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SomeService {

    public String handleMessage(String request) {
        log.info("handleMessage : received message [{}]", request);
        return request + " from Some Service";

    }
}
