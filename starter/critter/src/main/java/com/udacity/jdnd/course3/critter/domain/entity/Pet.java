package com.udacity.jdnd.course3.critter.domain.entity;

import java.time.LocalDate;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "pet")
public class Pet {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private long id;

  @OneToOne
  @JoinColumn(name = "pet_type_id", referencedColumnName = "id")
  private PetType petType;

  @Column(name = "name")
  private String name;

  @ManyToOne
  @JoinColumn(name = "customer_id")
  private Customer customer;

  @Column(name = "birth_date")
  private LocalDate birthDate;

  @Column(name = "notes")
  private String notes;

  @ManyToMany(mappedBy = "pets")
  private Set<Schedule> schedulePet;

  public Pet() {
  }

  public Set<Schedule> getSchedulePet() {
    return schedulePet;
  }

  public void setSchedulePet(
      Set<Schedule> schedulePet) {
    this.schedulePet = schedulePet;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public PetType getPetType() {
    return petType;
  }

  public void setPetType(PetType petType) {
    this.petType = petType;
  }

  public Customer getCustomer() {
    return customer;
  }

  public void setCustomer(Customer customer) {
    this.customer = customer;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public LocalDate getBirthDate() {
    return birthDate;
  }

  public void setBirthDate(LocalDate birthDate) {
    this.birthDate = birthDate;
  }

  public String getNotes() {
    return notes;
  }

  public void setNotes(String notes) {
    this.notes = notes;
  }
}
