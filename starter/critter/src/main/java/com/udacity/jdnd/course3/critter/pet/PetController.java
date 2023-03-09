package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.entities.Pet;
import com.udacity.jdnd.course3.critter.utils.Converter;
import javassist.NotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles web requests related to Pets.
 */
@RestController
@RequestMapping("/pet")
public class PetController {

    private final PetService petService;

    PetController(PetService petService) {

        this.petService = petService;
    }

    @PostMapping
    public PetDTO savePet(@RequestBody PetDTO petDTO) {
        Pet pet = petService.savePet(Converter.convertFromPetDTOToPet(petDTO), petDTO.getOwnerId());
        return Converter.convertFromPetToPetDTO(pet);
    }

    @GetMapping("/{petId}")
    public PetDTO getPet(@PathVariable long petId) {
        Pet pet = petService.getPet(petId);
        return Converter.convertFromPetToPetDTO(pet);
    }

    @GetMapping
    public List<PetDTO> getPets() {
        List<Pet> pets = petService.getPets();
        List<PetDTO> petDTOS = new ArrayList<>();

        for (Pet pet : pets) {
            petDTOS.add(Converter.convertFromPetToPetDTO(pet));
        }
        return petDTOS;
    }

    @GetMapping("/owner/{ownerId}")
    public List<PetDTO> getPetsByOwner(@PathVariable long ownerId) {
        List<Pet> pets = petService.getPetsByOwner(ownerId);
        List<PetDTO> petDTOS = new ArrayList<>();

        for (Pet pet : pets) {
            petDTOS.add(Converter.convertFromPetToPetDTO(pet));
        }
        return petDTOS;
    }
}
