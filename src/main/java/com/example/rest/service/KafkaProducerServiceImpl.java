package com.example.rest.service;

import com.example.rest.model.EmployersModel;
import com.example.rest.service.interfaces.KafkaProducerInterface;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Service
@RequiredArgsConstructor
@Log4j2
public class KafkaProducerServiceImpl implements KafkaProducerInterface {
    private final KafkaTemplate<String, EmployersModel> kafkaTemplate;
    @Override
    public void sendMessage(@NonNull EmployersModel message,@NonNull String topic) {

        ListenableFuture<SendResult<String, EmployersModel>> future = kafkaTemplate.send(topic, message);
        future.addCallback(new ListenableFutureCallback<>() {
            @Override
            public void onSuccess(SendResult<String, EmployersModel> result) {
                log.info("Send message=[" + message + "] with offset=[" + result +"]");
                log.info("Send message:{}", result.getProducerRecord());
            }

            @Override
            public void onFailure(Throwable ex) {
                System.out.println("Unable to send message=[" + message + "] due to : " + ex.getMessage());
            }
        });
    }

}
