package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.entities.Customer;
import com.udacity.jdnd.course3.critter.entities.Employee;
import com.udacity.jdnd.course3.critter.entities.Pet;
import com.udacity.jdnd.course3.critter.pet.PetRepository;
import com.udacity.jdnd.course3.critter.utils.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.udacity.jdnd.course3.critter.Constants.Constants.Exception.CUSTOMER_NOT_FOUND;
import static com.udacity.jdnd.course3.critter.Constants.Constants.Exception.EMPLOYEE_NOT_FOUND;

@Slf4j
@Service
public class UserService {
    //TODO: Add description before each method

    private final EmployeeRepository employeeRepository;
    private final CustomerRepository customerRepository;
    private final PetRepository petRepository;

    public UserService(EmployeeRepository employeeRepository, CustomerRepository customerRepository, PetRepository petRepository) {
        this.employeeRepository = employeeRepository;
        this.customerRepository = customerRepository;
        this.petRepository = petRepository;
    }

    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer getOwnerByPet(long petId) {
        Pet pet = petRepository.findById(petId).orElse(null);

        if (pet == null)
            throw new NotFoundException(CUSTOMER_NOT_FOUND);
        ;

        return pet.getCustomer();


    }

    public Employee saveEmployee(Employee employee) { //Done
        return employeeRepository.save(employee);
    }

    public Employee getEmployee(long employeeId) {
        Employee employee = employeeRepository.findById(employeeId).orElse(null);

        if (employee == null)
            throw new NotFoundException(EMPLOYEE_NOT_FOUND);

        return employee;
    }

    public void setAvailability(Set<DayOfWeek> daysAvailable, long employeeId) {
        Employee employee = employeeRepository.findById(employeeId).orElse(null);

        if (employee == null)
            throw new NotFoundException(EMPLOYEE_NOT_FOUND);

        employee.setDaysAvailable(daysAvailable);
        employeeRepository.save(employee);

    }

    public List<Employee> findEmployeesForService(Employee employee) {
        List<Employee> availableEmployees = employeeRepository.findAll().
                stream()
                .filter(e -> e.getDaysAvailable().contains(employee.getDaysAvailable().stream().findFirst().orElse(null))
                        && e.getSkills().containsAll(employee.getSkills())).collect(Collectors.toList());

        return availableEmployees;

    }

}
