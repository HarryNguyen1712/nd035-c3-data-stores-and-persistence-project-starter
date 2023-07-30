package com.udacity.jdnd.course3.critter.domain.entity;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "customer")
public class Customer extends User {

  @Column(name = "phone_number")
  private String phoneNumber;

  @Column(name = "notes")
  private String notes;

  @OneToMany(mappedBy = "customer")
  private List<Pet> petList;

  public Customer() {
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public String getNotes() {
    return notes;
  }

  public void setNotes(String notes) {
    this.notes = notes;
  }

  public List<Pet> getPetList() {
    return petList;
  }

  public void setPetList(List<Pet> petList) {
    this.petList = petList;
  }
}
