package com.udacity.jdnd.course3.critter.domain.entity;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "employee")
public class Employee extends User {

  @ManyToMany
  @JoinTable(
      name = "employee_skill",
      joinColumns = @JoinColumn(name = "employee_id"),
      inverseJoinColumns = @JoinColumn(name = "skill_id"))
  private Set<Skill> skills;

  @ManyToMany
  @JoinTable(
      name = "day_available",
      joinColumns = @JoinColumn(name = "employee_id"),
      inverseJoinColumns = @JoinColumn(name = "day_id"))
  private Set<DayOfWeek> daysAvailable;

  @ManyToMany(mappedBy = "employees")
  private Set<Schedule> scheduleEmployee;

  public Employee() {
  }

  public Set<Schedule> getScheduleEmployee() {
    return scheduleEmployee;
  }

  public void setScheduleEmployee(
      Set<Schedule> scheduleEmployee) {
    this.scheduleEmployee = scheduleEmployee;
  }

  public Set<Skill> getSkills() {
    return skills;
  }

  public void setSkills(Set<Skill> skills) {
    this.skills = skills;
  }

  public Set<DayOfWeek> getDaysAvailable() {
    return daysAvailable;
  }

  public void setDaysAvailable(
      Set<DayOfWeek> daysAvailable) {
    this.daysAvailable = daysAvailable;
  }
}
