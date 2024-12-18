package com.example.rest.controller;

import com.example.rest.model.EmployersModel;
import com.example.rest.service.EmployersService;
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

    private final EmployersService employersService;

    @GetMapping("/employers")
    public ResponseEntity<List<EmployersModel>> getEmployers() {

        return new ResponseEntity<>(employersService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/employers/{id}")
    public ResponseEntity<EmployersModel> getEmployerById(@PathVariable Long id) {

        return new ResponseEntity<>(employersService.findById(id), HttpStatus.OK);
    }

    @PostMapping(value = "/employers")
    public ResponseEntity<EmployersModel> addEmployer(@RequestBody EmployersModel employersModel) {
        return new ResponseEntity<>(employersService.add(employersModel), HttpStatus.CREATED);
    }

    @PutMapping("/employers/{id}")
    public ResponseEntity<EmployersModel> updateEmployer(@PathVariable Long id, @RequestBody EmployersModel employersModel) {

        return new ResponseEntity<>(employersService.replace(id, employersModel),HttpStatus.OK);
    }

    @DeleteMapping("/employers/{id}")
    public ResponseEntity<Void>deleteEmployer(@PathVariable Long id) {
        employersService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}