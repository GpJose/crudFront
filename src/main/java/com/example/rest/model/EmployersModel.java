package com.example.rest.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(schema = "dev", name = "employers")
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class EmployersModel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employersSequence")
    @SequenceGenerator(name = "employersSequence", sequenceName = "employersSequence", allocationSize = 1, schema = "dev")
    @Column(name = "id")
    private Long id;
    @Column(name = "firstName")
    private String firstName;
    @Column(name = "secondName")
    private String secondName;
    @Column(name = "lastName")
    private String lastName;
    @Column(name = "age")
    private Integer age;
    @Column(name = "position")
    private String position;
    @Column(name = "salary")
    private Integer salary;
}
