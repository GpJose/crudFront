package com.example.rest.controller;

import com.example.rest.model.EmployersModel;
import com.example.rest.model.enums.KafkaTopicsEnums;
import com.example.rest.service.EmployersServiceImpl;
import com.example.rest.service.interfaces.KafkaProducerInterface;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/")
@RequiredArgsConstructor
@Log4j2
public class ApiController {
    private final EmployersServiceImpl employersServiceImpl;

    private final KafkaProducerInterface kafkaProducerInterface;

    @GetMapping("/employers")
    public ResponseEntity<List<EmployersModel>> getEmployers() {

        return new ResponseEntity<>(employersServiceImpl.getAll(), HttpStatus.OK);
    }

    @GetMapping("/employers/{id}")
    public ResponseEntity<EmployersModel> getEmployerById(@PathVariable Long id) {

        return new ResponseEntity<>(employersServiceImpl.findById(id), HttpStatus.OK);
    }

    @PostMapping(value = "/employers")
    public ResponseEntity<HttpStatus> addEmployer(@RequestBody EmployersModel employersModel) throws JsonProcessingException {
            kafkaProducerInterface.sendMessage(employersModel, KafkaTopicsEnums.ADD.getTopic());

            return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/employers/{id}")
    public ResponseEntity<HttpStatus> updateEmployer(@PathVariable Long id, @RequestBody EmployersModel employersModel) throws JsonProcessingException {
        employersModel.setId(id);
        kafkaProducerInterface.sendMessage(employersModel, KafkaTopicsEnums.UPDATE.getTopic());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/employers/{id}")
    public ResponseEntity<HttpStatus>deleteEmployer(@PathVariable Long id) throws JsonProcessingException {
        kafkaProducerInterface.sendMessage(EmployersModel.builder()
                        .id(id)
                .build(), KafkaTopicsEnums.DELETE.getTopic());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}