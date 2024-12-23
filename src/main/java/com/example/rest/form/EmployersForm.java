package com.example.rest.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

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
