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
       return null;
    }

    @GetMapping("/customer")
    public List<CustomerDTO> getAllCustomers(){

        List<Customer> customers=customerService.getAllCustomer();
        return customers.stream().map(this::enitityToCustomerDTO).collect(Collectors.toList());

    }

    @GetMapping("/customer/pet/{petId}")
    public CustomerDTO getOwnerByPet(@PathVariable long petId){
        throw new UnsupportedOperationException();
    }

    @PostMapping("/employee")
    public EmployeeDTO saveEmployee(@RequestBody EmployeeDTO employeeDTO) {
        throw new UnsupportedOperationException();
    }

    @PostMapping("/employee/{employeeId}")
    public EmployeeDTO getEmployee(@PathVariable long employeeId) {
        throw new UnsupportedOperationException();
    }

    @PutMapping("/employee/{employeeId}")
    public void setAvailability(@RequestBody Set<DayOfWeek> daysAvailable, @PathVariable long employeeId) {
        throw new UnsupportedOperationException();
    }

    @GetMapping("/employee/availability")
    public List<EmployeeDTO> findEmployeesForService(@RequestBody EmployeeRequestDTO employeeDTO) {
        throw new UnsupportedOperationException();
    }

}
