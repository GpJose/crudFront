package com.example.rest.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum KafkaTopicsEnums {
    DELETE("delete"),
    ADD("add"),
    UPDATE("update");
    private String topic;
}
