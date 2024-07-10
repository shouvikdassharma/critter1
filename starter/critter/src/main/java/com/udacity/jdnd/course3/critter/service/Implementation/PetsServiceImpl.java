package com.udacity.jdnd.course3.critter.service.Implementation;

import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.entity.Pets;
import com.udacity.jdnd.course3.critter.repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.repository.PetsRepository;
import com.udacity.jdnd.course3.critter.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
@Service
@Transactional
public class PetsServiceImpl implements PetService {

    @Autowired
    PetsRepository petsRepository;
    @Autowired
    CustomerRepository customerRepository;

    @Override
    public Pets save(Pets pets,Long id) {

        Customer customer=customerRepository.getOne(id);
        List<Pets> pets1 =new ArrayList<>();
        pets.setCustomer(customer);
        pets=petsRepository.save(pets);
        pets1.add(pets);
        customer.setPets(pets1);
        customerRepository.save(customer);
        return  pets;
    }

    @Override
    public Pets findPetsById(Long id) {
        return petsRepository.getOne(id);
    }

    @Override
    public List<Pets> findByCustomerId(Long custId) {

        return petsRepository.findPetsByCustomerId(custId);
    }
    public List<Pets> getAllPets()
    {
        return petsRepository.findAll();

    }


}
