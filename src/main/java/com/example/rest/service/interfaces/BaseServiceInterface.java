package com.example.rest.service.interfaces;

import com.example.rest.exception.EmployersNotFoundException;

import java.util.List;

public interface BaseServiceInterface <T> {

    T add(T id);
    T replace(Long id, T entity) throws EmployersNotFoundException;
    void delete(Long id) throws EmployersNotFoundException;
    T findById(Long id) throws EmployersNotFoundException;
    List<T> getAll() throws EmployersNotFoundException;
}
