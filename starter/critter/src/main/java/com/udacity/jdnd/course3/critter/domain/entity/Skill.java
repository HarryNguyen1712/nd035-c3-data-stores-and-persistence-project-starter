package com.udacity.jdnd.course3.critter.domain.entity;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "skill")
public class Skill {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private long id;

  @Column(name = "name")
  private String name;

  @ManyToMany(mappedBy = "skills")
  private Set<Employee> employees;

  @ManyToMany(mappedBy = "activities")
  private Set<Schedule> scheduleSkill;

  public Skill() {
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Set<Employee> getEmployees() {
    return employees;
  }

  public void setEmployees(
      Set<Employee> employees) {
    this.employees = employees;
  }

  public Set<Schedule> getScheduleSkill() {
    return scheduleSkill;
  }

  public void setScheduleSkill(
      Set<Schedule> scheduleSkill) {
    this.scheduleSkill = scheduleSkill;
  }
}
