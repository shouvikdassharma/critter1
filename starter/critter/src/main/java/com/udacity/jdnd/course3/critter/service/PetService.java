package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.entity.Pets;

import java.util.List;

public interface PetService {
    Pets save(Pets pets,Long id);
    Pets findPetsById(Long id);
    List<Pets> findByCustomerId(Long custId);
    List<Pets> getAllPets();

}
