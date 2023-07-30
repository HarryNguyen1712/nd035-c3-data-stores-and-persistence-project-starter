package com.udacity.jdnd.course3.critter.domain.service;

import com.udacity.jdnd.course3.critter.domain.entity.Customer;
import com.udacity.jdnd.course3.critter.domain.entity.Pet;
import com.udacity.jdnd.course3.critter.domain.entity.PetType;
import com.udacity.jdnd.course3.critter.domain.repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.domain.repository.PetRepository;
import com.udacity.jdnd.course3.critter.domain.repository.PetTypeRepository;
import com.udacity.jdnd.course3.critter.pet.PetDTO;
import com.udacity.jdnd.course3.critter.pet.PetMapper;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class PetService {

  private final PetRepository petRepository;

  private final CustomerRepository customerRepository;

  private final PetTypeRepository petTypeRepository;

  private final PetMapper petMapper;

  public PetService(PetRepository petRepository, CustomerRepository customerRepository,
                    PetTypeRepository petTypeRepository, PetMapper petMapper) {
    this.petRepository = petRepository;
    this.customerRepository = customerRepository;
    this.petTypeRepository = petTypeRepository;
    this.petMapper = petMapper;
  }

  @Transactional
  public PetDTO savePet(PetDTO petDTO) {
    Pet pet = new Pet();

    Optional<Customer> customer = customerRepository.findById(petDTO.getOwnerId());
    Optional<PetType> petType = petTypeRepository.findByName(petDTO.getType().name());
    if (petType.isPresent()) {
      pet.setPetType(petType.get());
    } else {
      throw new RuntimeException();
    }
    pet.setName(petDTO.getName());
    pet.setNotes(petDTO.getNotes());
    pet.setBirthDate(petDTO.getBirthDate());
    if (customer.isPresent()) {
      Customer customer1 = customer.get();
      pet.setCustomer(customer1);
      List<Pet> petList = customer1.getPetList();
      petList.add(pet);
      customer1.setPetList(petList);
    } else {
      throw new RuntimeException();
    }


    return petMapper.entityToDto(petRepository.save(pet));
  }

  public List<PetDTO> getPets(Long ownerId) {
    List<PetDTO> petDTOList;
    if (Objects.isNull(ownerId)) {
      petDTOList = petMapper.entityToDtoList(petRepository.findAll());
    } else {
      petDTOList =
          petMapper.entityToDtoList(petRepository.findAllByCustomer_Id(ownerId));
    }
    return petDTOList;
  }

  public PetDTO getPetById(Long petId) {
    Optional<Pet> pet = petRepository.findById(petId);
    if (pet.isPresent()) {
      return petMapper.entityToDto(pet.get());
    } else {
      throw new RuntimeException();
    }
  }
}
