package com.udacity.jdnd.course3.critter.entities;

import com.udacity.jdnd.course3.critter.pet.PetType;
import lombok.Data;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table
public class Pet {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private PetType type;
    @Column(length = 12)
    private String name;
    private LocalDate birthDate;
    @Column(columnDefinition = "nvarchar(MAX)")
    private String notes;
    @ManyToOne(optional = false)
    @JoinColumn(name = "customer_id")
    @NotFound(action = NotFoundAction.IGNORE)
    public Customer customer;

    @ManyToMany(mappedBy = "pets")
    List<Schedule> schedules;

}
