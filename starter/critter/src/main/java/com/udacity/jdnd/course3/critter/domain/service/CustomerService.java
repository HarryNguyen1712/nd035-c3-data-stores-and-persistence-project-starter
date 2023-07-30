package com.udacity.jdnd.course3.critter.domain.service;

import com.udacity.jdnd.course3.critter.domain.entity.Customer;
import com.udacity.jdnd.course3.critter.domain.repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.domain.repository.PetRepository;
import com.udacity.jdnd.course3.critter.user.CustomerDTO;
import com.udacity.jdnd.course3.critter.user.CustomerMapper;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

  private final CustomerRepository customerRepository;

  private final CustomerMapper customerMapper;

  public CustomerService(CustomerRepository customerRepository, CustomerMapper customerMapper) {
    this.customerRepository = customerRepository;
    this.customerMapper = customerMapper;
  }

  public CustomerDTO saveCustomer(CustomerDTO customerDTO) {
    Customer customer = new Customer();
    customer.setName(customerDTO.getName());
    customer.setNotes(customerDTO.getNotes());
    customer.setPhoneNumber(customerDTO.getPhoneNumber());
    customer.setPetList(new ArrayList<>());
    return customerMapper.entityToDto(customerRepository.save(customer));
  }

  public List<CustomerDTO> getAllCustomers() {
    return customerMapper.entityToDtoList(customerRepository.findAll());
  }

  public CustomerDTO getCustomerByPet(Long petId) {
    return customerMapper.entityToDto(customerRepository.findByPet(petId));
  }
}
