package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.entity.Pets;
import com.udacity.jdnd.course3.critter.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Pets.
 */
@RestController
@RequestMapping("/pet")
public class PetController {

    @Autowired
    PetService petService;

    private PetDTO convertPetEntityToDTO(Pets pets)
    {
        PetDTO petDTO = new PetDTO();
        petDTO.setId(pets.getId());
        petDTO.setBirthDate(pets.getBirthDate());
        petDTO.setName(pets.getName());
        petDTO.setNotes(pets.getNotes());
        petDTO.setOwnerId(pets.getCustomer().getId());
        petDTO.setType(pets.getPetType());
        return  petDTO;
    }

    @PostMapping
    public PetDTO savePet(@RequestBody PetDTO petDTO) {
        Pets pets=new Pets(petDTO.getId(),petDTO.getType(),petDTO.getName(),petDTO.getCustomer(),petDTO.getBirthDate(),petDTO.getNotes());
        PetDTO dtoPet;
        try {

            dtoPet=convertPetEntityToDTO(petService.save(pets,petDTO.getOwnerId()));

        }
        catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "pets not saved", e);
        }
        return dtoPet;
    }

    @GetMapping("/{petId}")
    public PetDTO getPet(@PathVariable long petId) {
        Pets pets;
        try {
            pets=petService.findPetsById(petId);
        }
        catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"pets id not found" + petId,e);
        }
        return convertPetEntityToDTO(pets);
    }

    @GetMapping
    public List<PetDTO> getPets(){
        List<Pets> pets;
        try {
            pets=petService.getAllPets();
        }
        catch (Exception e)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"pet not found" ,e);
        }

        return pets.stream().map(this::convertPetEntityToDTO).collect(Collectors.toList());
    }

    @GetMapping("/owner/{ownerId}")
    public List<PetDTO> getPetsByOwner(@PathVariable long ownerId) {
        List<Pets> pets;
        try {
            pets=petService.findByCustomerId(ownerId);
        }
        catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST," Owner id not found" ,e);
        }
        return pets.stream().map(this::convertPetEntityToDTO).collect(Collectors.toList());
    }
}
