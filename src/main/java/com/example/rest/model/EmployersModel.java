package com.example.rest.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(schema = "dev", name = "employers")
@Getter
@Setter
@NoArgsConstructor
@Builder
@ToString
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmployersModel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employersSequence")
    @SequenceGenerator(name = "employersSequence", sequenceName = "employersSequence", allocationSize = 1, schema = "dev")
    @Column(name = "id")
    private Long id;
    @JsonProperty(value = "firstName")
    @Column(name = "firstName")
    private String firstName;
    @JsonProperty(value = "secondName")
    @Column(name = "secondName")
    private String secondName;
    @JsonProperty(value = "lastName")
    @Column(name = "lastName")
    private String lastName;
    @JsonProperty(value = "age")
    @Column(name = "age")
    private Integer age;
    @JsonProperty(value = "position")
    @Column(name = "position")
    private String position;
    @JsonProperty(value = "salary")
    @Column(name = "salary")
    private Integer salary;
}
