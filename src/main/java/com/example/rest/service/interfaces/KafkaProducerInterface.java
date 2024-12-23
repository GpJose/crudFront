package com.example.rest.service.interfaces;

import com.example.rest.model.EmployersModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.NonNull;

public interface KafkaProducerInterface {
    void sendMessage(@NonNull EmployersModel employersModel, @NonNull String topic) throws JsonProcessingException;
}
