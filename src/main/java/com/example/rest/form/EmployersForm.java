package com.example.rest.form;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployersForm {

    @NotNull(message = "id can't be null")
    @Pattern(regexp = "^\\d+$", message = "id must have only digits")
    private Long id;
    @Pattern(regexp = "^\\D+$")
    private String firstName;
    @Pattern(regexp = "^\\D+$")
    private String secondName;
    @Pattern(regexp = "^\\D+$")
    private String lastName;
    @Size(min = 18, message = "Age more than 18")
    @Pattern(regexp = "^\\d+$", message = "age must have only digits")
    private Integer age;
    private String position;
    @Pattern(regexp = "^\\d+$", message = "salary must have only digits")
    private Integer salary;
}
