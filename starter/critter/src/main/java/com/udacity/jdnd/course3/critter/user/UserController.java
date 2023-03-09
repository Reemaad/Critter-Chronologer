package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.entities.Customer;
import com.udacity.jdnd.course3.critter.entities.Employee;
import com.udacity.jdnd.course3.critter.utils.Converter;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Handles web requests related to Users.
 * <p>
 * Includes requests for both customers and employees. Splitting this into separate user and customer controllers
 * would be fine too, though that is not part of the required scope for this class.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/customer")
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO) {
        Customer customer = userService.saveCustomer(Converter.convertFromCustomerDTOToCustomer(customerDTO));
        return Converter.convertFromCustomerToCustomerDTO(customer);
    }

    @GetMapping("/customer")
    public List<CustomerDTO> getAllCustomers() {
        List<Customer> customers = userService.getAllCustomers();
        List<CustomerDTO> customerDTOS = new ArrayList<>();

        for (Customer customer: customers) {
            customerDTOS.add(Converter.convertFromCustomerToCustomerDTO(customer));
        }
        return customerDTOS;
    }

    @GetMapping("/customer/pet/{petId}")
    public CustomerDTO getOwnerByPet(@PathVariable long petId) {
        Customer customer = userService.getOwnerByPet(petId);
        return Converter.convertFromCustomerToCustomerDTO(customer);
    }

    @PostMapping("/employee")
    public EmployeeDTO saveEmployee(@RequestBody EmployeeDTO employeeDTO) {
        Employee employee = userService.saveEmployee(Converter.convertFromEmployeeDTOToEmployee(employeeDTO));
        return Converter.convertFromEmployeeToEmployeeDTO(employee);
    }

    @GetMapping("/employee/{employeeId}")
    public EmployeeDTO getEmployee(@PathVariable long employeeId) {
       Employee employee = userService.getEmployee(employeeId);
        return Converter.convertFromEmployeeToEmployeeDTO(employee);
    }

    @PutMapping("/employee/{employeeId}") //Done
    public void setAvailability(@RequestBody Set<DayOfWeek> daysAvailable, @PathVariable long employeeId) {
        userService.setAvailability(daysAvailable,employeeId);
    }

    @GetMapping("/employee/availability") //Done
    public List<EmployeeDTO> findEmployeesForService(@RequestBody EmployeeRequestDTO employeeRequestDTO) {
        List<Employee> employees = userService.findEmployeesForService(Converter.convertFromEmployeeRequestDTOToEmployee(employeeRequestDTO));
        List<EmployeeDTO> employeeDTOS = new ArrayList<>();

        for (Employee employee: employees) {
            employeeDTOS.add(Converter.convertFromEmployeeToEmployeeDTO(employee));
        }
        return employeeDTOS;
    }

}
