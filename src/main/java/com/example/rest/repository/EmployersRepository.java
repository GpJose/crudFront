package com.example.rest.repository;

import com.example.rest.model.EmployersModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployersRepository extends JpaRepository<EmployersModel, Long> {

}
