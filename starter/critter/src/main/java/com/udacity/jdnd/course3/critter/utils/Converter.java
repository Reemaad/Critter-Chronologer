package com.udacity.jdnd.course3.critter.utils;

import com.udacity.jdnd.course3.critter.entities.Customer;
import com.udacity.jdnd.course3.critter.entities.Employee;
import com.udacity.jdnd.course3.critter.entities.Pet;
import com.udacity.jdnd.course3.critter.entities.Schedule;
import com.udacity.jdnd.course3.critter.pet.PetDTO;
import com.udacity.jdnd.course3.critter.schedule.ScheduleDTO;
import com.udacity.jdnd.course3.critter.user.CustomerDTO;
import com.udacity.jdnd.course3.critter.user.EmployeeDTO;
import com.udacity.jdnd.course3.critter.user.EmployeeRequestDTO;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.Collections;

public class Converter {

    public static Customer convertFromCustomerDTOToCustomer(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerDTO, customer);
        return customer;
    }

    public static CustomerDTO convertFromCustomerToCustomerDTO(Customer customer) {
        CustomerDTO customerDTO = new CustomerDTO();
        BeanUtils.copyProperties(customer, customerDTO);

        if (customer.getPets() != null) {
            customerDTO.setPetIds(new ArrayList<>());
            for (Pet p : customer.getPets()) {
                customerDTO.getPetIds().add(p.getId());
            }
        }
        return customerDTO;
    }

    public static Employee convertFromEmployeeDTOToEmployee(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeDTO, employee);
        return employee;
    }

    public static EmployeeDTO convertFromEmployeeToEmployeeDTO(Employee employee) {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        BeanUtils.copyProperties(employee, employeeDTO);
        return employeeDTO;
    }

    public static Employee convertFromEmployeeRequestDTOToEmployee(EmployeeRequestDTO employeeRequestDTO) {
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeRequestDTO, employee);
        employee.setDaysAvailable(Collections.singleton(employeeRequestDTO.getDate().getDayOfWeek()));
        return employee;
    }

    public static Pet convertFromPetDTOToPet(PetDTO petDTO) {
        Pet pet = new Pet();
        BeanUtils.copyProperties(petDTO, pet);
        return pet;
    }

    public static PetDTO convertFromPetToPetDTO(Pet pet) {
        PetDTO petDTO = new PetDTO();
        BeanUtils.copyProperties(pet, petDTO);
        petDTO.setOwnerId(pet.getCustomer() != null ? pet.getCustomer().getId() : null);
        return petDTO;
    }

    public static Schedule convertFromScheduleDTOToSchedule(ScheduleDTO scheduleDTO) {
        Schedule schedule = new Schedule();
        BeanUtils.copyProperties(scheduleDTO, schedule);
        return schedule;
    }

    public static ScheduleDTO convertFromScheduleToScheduleDTO(Schedule schedule) {
        ScheduleDTO scheduleDTO = new ScheduleDTO();
        BeanUtils.copyProperties(schedule, scheduleDTO);

        if (schedule.getEmployees() != null) {
            scheduleDTO.setEmployeeIds(new ArrayList<>());
            for (Employee employee : schedule.getEmployees()) {
                scheduleDTO.getEmployeeIds().add(employee.getId());
            }
        }

        if (schedule.getPets() != null) {
            scheduleDTO.setPetIds(new ArrayList<>());
            for (Pet p : schedule.getPets()) {
                scheduleDTO.getPetIds().add(p.getId());
            }
        }
        return scheduleDTO;

    }
}
