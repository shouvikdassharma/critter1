package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.entity.Pets;

import java.util.List;

public interface CustomerService {
    Customer save(Customer customer,List<Long> id);
    Customer getCustomerByPetId(Pets pets);

    public List<Customer> getAllCustomer();
    Customer getCustomerById(Long id);
}
