package com.udacity.jdnd.course3.critter.user;

import lombok.Data;

import java.util.List;

/**
 * Represents the form that customer request and response data takes. Does not map
 * to the database directly.
 */
@Data
public class CustomerDTO {
    private Long id;
    private String name;
    private String phoneNumber;
    private String notes;
    private List<Long> petIds;

}
