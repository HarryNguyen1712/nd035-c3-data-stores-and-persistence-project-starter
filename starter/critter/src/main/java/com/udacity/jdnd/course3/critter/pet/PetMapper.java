package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.common.PetTypeEnum;
import com.udacity.jdnd.course3.critter.domain.entity.Pet;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class PetMapper {

  public PetDTO entityToDto(Pet pet) {
    PetDTO petDTO = new PetDTO();
    petDTO.setId(pet.getId());
    petDTO.setName(pet.getName());
    petDTO.setType(PetTypeEnum.valueOf(pet.getPetType().getName()));
    petDTO.setBirthDate(pet.getBirthDate());
    petDTO.setNotes(pet.getNotes());
    petDTO.setOwnerId(pet.getCustomer().getId());
    return petDTO;
  }

  public List<PetDTO> entityToDtoList(List<Pet> pets) {
    List<PetDTO> petDTOList = new ArrayList<>();
    for (Pet pet : pets) {
      petDTOList.add(entityToDto(pet));
    }
    return petDTOList;
  }
}
