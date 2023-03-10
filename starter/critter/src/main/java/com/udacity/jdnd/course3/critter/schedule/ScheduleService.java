package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.entities.Customer;
import com.udacity.jdnd.course3.critter.entities.Employee;
import com.udacity.jdnd.course3.critter.entities.Pet;
import com.udacity.jdnd.course3.critter.entities.Schedule;
import com.udacity.jdnd.course3.critter.pet.PetRepository;
import com.udacity.jdnd.course3.critter.user.CustomerRepository;
import com.udacity.jdnd.course3.critter.user.EmployeeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ScheduleService {
    private final EmployeeRepository employeeRepository;
    private final CustomerRepository customerRepository;
    private final PetRepository petRepository;
    private final ScheduleRepository scheduleRepository;

    public ScheduleService(ScheduleRepository scheduleRepository, EmployeeRepository employeeRepository, CustomerRepository customerRepository, PetRepository petRepository) {
        this.scheduleRepository = scheduleRepository;
        this.employeeRepository = employeeRepository;
        this.customerRepository = customerRepository;
        this.petRepository = petRepository;
    }

    public Schedule createSchedule(Schedule schedule, List<Long> employeeIds, List<Long> petIds) {
        List<Employee> employees = employeeRepository.findAllById(employeeIds);
        List<Pet> pets = petRepository.findAllById(petIds);

        schedule.setEmployees(employees);
        schedule.setPets(pets);

        Schedule s = scheduleRepository.save(schedule);

        for (Employee employee : employees) {
            if (employee.getSchedules() == null)
                employee.setSchedules(new ArrayList<>());
            employee.getSchedules().add(schedule);
        }

        for (Pet pet : pets) {
            if (pet.getSchedules() == null)
                pet.setSchedules(new ArrayList<>());
            pet.getSchedules().add(schedule);
        }

        List<Employee> e = employeeRepository.saveAll(employees);
        List<Pet> p = petRepository.saveAll(pets);


        return s;
    }

    public List<Schedule> getAllSchedules() {
        return scheduleRepository.findAll();
    }

    public List<Schedule> getScheduleForPet(long petId) {
        Pet pet = petRepository.findById(petId).orElse(null);
        return pet.getSchedules();
    }

    public List<Schedule> getScheduleForEmployee(long employeeId) {
        Employee employee = employeeRepository.findById(employeeId).orElse(null);
        return employee.getSchedules();
    }

    public List<Schedule> getScheduleForCustomer(long customerId) {
        Customer customer = customerRepository.findById(customerId).orElse(null);
        List<Schedule> schedules = new ArrayList<>();
        for (Pet pet : customer.getPets()) {
            schedules.addAll(pet.getSchedules());
        }

        return schedules;
    }
}
