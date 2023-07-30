package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.domain.entity.Customer;
import com.udacity.jdnd.course3.critter.domain.entity.Pet;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {

  public CustomerDTO entityToDto(Customer customer) {
    CustomerDTO customerDTO = new CustomerDTO();
    customerDTO.setId(customer.getId());
    customerDTO.setName(customer.getName());
    customerDTO.setNotes(customer.getNotes());
    customerDTO.setPhoneNumber(customer.getPhoneNumber());
    List<Long> petIds = new ArrayList<>();
    if (customer.getPetList() != null) {
      for (Pet pet : customer.getPetList()) {
        petIds.add(pet.getId());
      }
    }
    customerDTO.setPetIds(petIds);
    return customerDTO;
  }

  public List<CustomerDTO> entityToDtoList(List<Customer> customers) {
    List<CustomerDTO> customerDTOList = new ArrayList<>();
    for (Customer customer : customers) {
      customerDTOList.add(entityToDto(customer));
    }
    return customerDTOList;
  }
}
