package com.udacity.jdnd.course3.critter.entities;

import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table
public class Employee {
    @Id
    @GeneratedValue
    private Long id;
    @Column(length = 12)
    private String name;

    @ElementCollection
    private Set<EmployeeSkill> skills;

    @ElementCollection
    private Set<DayOfWeek> daysAvailable;
    @ManyToMany(mappedBy = "employees")
    @ToString.Exclude
    public List<Schedule> schedules;

}
