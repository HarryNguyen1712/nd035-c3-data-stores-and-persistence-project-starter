package com.udacity.jdnd.course3.critter.domain.entity;

import java.time.LocalDate;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "schedule")
public class Schedule {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private long id;

  @ManyToMany
  @JoinTable(
      name = "schedule_employee",
      joinColumns = @JoinColumn(name = "employee_id"),
      inverseJoinColumns = @JoinColumn(name = "schedule_id"))
  private Set<Employee> employees;

  @ManyToMany
  @JoinTable(
      name = "schedule_pet",
      joinColumns = @JoinColumn(name = "pet_id"),
      inverseJoinColumns = @JoinColumn(name = "schedule_id"))
  private Set<Pet> pets;

  @Column(name = "date")
  private LocalDate date;

  @ManyToMany
  @JoinTable(
      name = "activities",
      joinColumns = @JoinColumn(name = "schedule_id"),
      inverseJoinColumns = @JoinColumn(name = "employee_skill_id"))
  private Set<Skill> activities;

  public Schedule() {
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public Set<Employee> getEmployees() {
    return employees;
  }

  public void setEmployees(
      Set<Employee> employees) {
    this.employees = employees;
  }

  public Set<Pet> getPets() {
    return pets;
  }

  public void setPets(Set<Pet> pets) {
    this.pets = pets;
  }

  public LocalDate getDate() {
    return date;
  }

  public void setDate(LocalDate date) {
    this.date = date;
  }

  public Set<Skill> getActivities() {
    return activities;
  }

  public void setActivities(Set<Skill> activities) {
    this.activities = activities;
  }
}
