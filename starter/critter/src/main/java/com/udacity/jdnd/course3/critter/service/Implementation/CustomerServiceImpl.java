package com.udacity.jdnd.course3.critter.service.Implementation;

import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.entity.Pets;
import com.udacity.jdnd.course3.critter.repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Customer save(Customer customer,List<Long> petsId) {

        return customerRepository.save(customer);
    }

    @Override
    public Customer getCustomerByPetId(Pets pets) {
        return customerRepository.findCustomerByPets(pets);
    }

    @Override
    public List<Customer> getAllCustomer() {
        return  customerRepository.findAll();
    }

    @Override
    public Customer getCustomerById(Long id) {
        return  customerRepository.getOne(id);
    }

}
