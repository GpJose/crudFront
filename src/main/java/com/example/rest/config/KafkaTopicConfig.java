package com.example.rest.config;

import com.example.rest.model.enums.KafkaTopicsEnums;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class KafkaTopicConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapAddress;

    @Bean
    public KafkaAdmin kafkaAdmin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        return new KafkaAdmin(configs);
    }

    @Bean
    public List<NewTopic> kafkaTopics() {
        return Arrays.asList(
                new NewTopic(KafkaTopicsEnums.ADD.getTopic(), 1, (short) 1),
                new NewTopic(KafkaTopicsEnums.DELETE.getTopic(), 1, (short) 1),
                new NewTopic(KafkaTopicsEnums.UPDATE.getTopic(), 1, (short) 1)
        );
    }
}