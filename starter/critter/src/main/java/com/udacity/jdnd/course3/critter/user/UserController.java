package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.entity.Pets;
import com.udacity.jdnd.course3.critter.service.CustomerService;
import com.udacity.jdnd.course3.critter.service.EmployeeService;
import org.checkerframework.checker.units.qual.C;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Users.
 *
 * Includes requests for both customers and employees. Splitting this into separate user and customer controllers
 * would be fine too, though that is not part of the required scope for this class.
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    CustomerService customerService;

    @Autowired
    EmployeeService employeeService;

    private CustomerDTO enitityToCustomerDTO(Customer customer)

    {
        List<Long> petIds=customer.getPets().stream().map(Pets::getId).collect(Collectors.toList());
        return new CustomerDTO(customer.getId(),customer.getName(),customer.getPhoneNumber(),customer.getNotes(),petIds);
    }

    private EmployeeDTO entityToEmployeeDTO(Employee employee)

    {

        return new EmployeeDTO(employee.getId(),employee.getName(),employee.getEmployeeSkills(),employee.getDayOfWeeks());
    }

    @PostMapping("/customer")
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO){
        List<Long> petId=customerDTO.getPetIds();
        Customer customer=new Customer(customerDTO.getId(), customerDTO.getName(), customerDTO.getPhoneNumber(), customerDTO.getNotes());
        CustomerDTO customerDTO1;
        try
        {
            customerDTO1=enitityToCustomerDTO(customerService.save(customer,petId));
        }
        catch (Exception e)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Customer  not  saved", e);
        }
       return customerDTO1;
    }

    @GetMapping("/customer")
    public List<CustomerDTO> getAllCustomers(){

        List<Customer> customers=customerService.getAllCustomer();
        return customers.stream().map(this::enitityToCustomerDTO).collect(Collectors.toList());

    }

    @GetMapping("/customer/pet/{petId}")
    public CustomerDTO getOwnerByPet(@PathVariable long petId){

        Customer customer;
        try
        {
            customer=customerService.getCustomerByPetId(petId);
        }catch (Exception e)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Owner pet with id: " + petId + " not found", e);
        }

        return enitityToCustomerDTO(customer);
    }

    @PostMapping("/employee")
    public EmployeeDTO saveEmployee(@RequestBody EmployeeDTO employeeDTO) {
        Employee employee=new Employee(employeeDTO.getId(),employeeDTO.getName(),employeeDTO.getSkills(),employeeDTO.getDaysAvailable());
        EmployeeDTO employeeDTO1;
        try {
            employeeDTO1=entityToEmployeeDTO(employeeService.save(employee));
        }
        catch (Exception e)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Employee not saved", e);
        }
        return employeeDTO1;
    }

    @PostMapping("/employee/{employeeId}")
    public EmployeeDTO getEmployee(@PathVariable long employeeId) {
        EmployeeDTO employeeDTO;
        try {
            employeeDTO=entityToEmployeeDTO(employeeService.findEmployeeById(employeeId));

        }
        catch (Exception e)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Employee pet with id: " + employeeId + " not found", e);
        }
        return employeeDTO;
    }

    @PutMapping("/employee/{employeeId}")
    public void setAvailability(@RequestBody Set<DayOfWeek> daysAvailable, @PathVariable long employeeId) {

        try {
            employeeService.setEmployeeAvailability(daysAvailable,employeeId);
        }
        catch (Exception e)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "EmployeeId pet with id: " + employeeId + " not found", e);
        }

    }

    @GetMapping("/employee/availability")
    public List<EmployeeDTO> findEmployeesForService(@RequestBody EmployeeRequestDTO employeeDTO) {
        List<Employee> employees;
        try
        {
            employees=employeeService.findEmployeeByDateAndSkills(employeeDTO.getDate(),employeeDTO.getSkills());

        }
        catch (Exception e)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Employee for sevice not found",e);
        }
        return employees.stream().map(this::entityToEmployeeDTO).collect(Collectors.toList());

    }

}
