package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.entities.Pet;
import com.udacity.jdnd.course3.critter.entities.Customer;
import com.udacity.jdnd.course3.critter.user.CustomerRepository;
import com.udacity.jdnd.course3.critter.utils.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.udacity.jdnd.course3.critter.Constants.Constants.Exception.CUSTOMER_NOT_FOUND;
import static com.udacity.jdnd.course3.critter.Constants.Constants.Exception.PET_NOT_FOUND;

@Slf4j
@Service
public class PetService {
    private final PetRepository petRepository;
    private final CustomerRepository customerRepository;

    public PetService(PetRepository petRepository, CustomerRepository customerRepository) {
        this.petRepository = petRepository;
        this.customerRepository = customerRepository;
    }

    public Pet savePet(Pet pet, long ownerId) {
        Customer customer = customerRepository.findById(ownerId).orElse(null);
        if (customer == null)
            throw new NotFoundException(CUSTOMER_NOT_FOUND);

        pet.setCustomer(customer);

        Pet p = petRepository.save(pet);

        if(customer.getPets()==null)
            customer.setPets(new ArrayList<>());

        customer.getPets().add(p);

       customerRepository.save(customer);

        return p;

    }

    public Pet getPet(long petId) {
        Pet pet = petRepository.findById(petId).orElse(null);

        if (pet == null)
            throw new NotFoundException(PET_NOT_FOUND);

        return pet;
    }

    public List<Pet> getPets() {//Done
        return petRepository.findAll();
    }

    public List<Pet> getPetsByOwner(long ownerId) {//Done
        Customer customer = customerRepository.findById(ownerId).orElse(null);

        if (customer == null)
            throw new NotFoundException(CUSTOMER_NOT_FOUND);

        return customer.getPets();
    }
}
