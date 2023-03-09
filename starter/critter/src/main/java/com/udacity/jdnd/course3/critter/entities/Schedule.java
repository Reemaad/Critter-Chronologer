package com.udacity.jdnd.course3.critter.entities;

import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table
public class Schedule {
    @Id
    @GeneratedValue
    private Long id;
    private LocalDate date;
    @ElementCollection
    private Set<EmployeeSkill> activities;

    @ManyToMany
    @JoinTable(
            name = "schedule_employee",
            joinColumns = {@JoinColumn(name = "schedule_id")},
            inverseJoinColumns = {@JoinColumn(name = "employee_id")}
    )
    @ToString.Exclude
    private List<Employee> employees;

    @ManyToMany
    @JoinTable(
            name = "schedule_pet",
            joinColumns = {@JoinColumn(name = "schedule_id")},
            inverseJoinColumns = {@JoinColumn(name = "pet_id")}
    )
    @ToString.Exclude
    private List<Pet> pets;
}
