package com.example.rest.service;

import com.example.rest.model.EmployersModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class KafkaConsumerService {
    private final EmployersServiceImpl employersService;
    @KafkaListener(topics = "add",groupId = "foo")
    public void add(EmployersModel message) {
        log.info("Add Message received : " + message);
        employersService.add(message);
    }
    @KafkaListener(topics = "delete", groupId = "foo")
    public void delete(EmployersModel message) {
        log.info("Delete Message received : " + message);
        employersService.delete(message.getId());
    }
    @KafkaListener(topics = "update", groupId = "foo")
    public void update(EmployersModel message) {
        log.info("Update Message received : " + message);
        employersService.replace(message.getId(), message);
    }
}
