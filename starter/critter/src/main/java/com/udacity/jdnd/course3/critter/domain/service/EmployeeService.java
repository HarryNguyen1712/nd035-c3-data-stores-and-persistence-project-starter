package com.udacity.jdnd.course3.critter.domain.service;

import com.udacity.jdnd.course3.critter.common.EmployeeSkillEnum;
import com.udacity.jdnd.course3.critter.domain.entity.DayOfWeek;
import com.udacity.jdnd.course3.critter.domain.entity.Employee;
import com.udacity.jdnd.course3.critter.domain.entity.Skill;
import com.udacity.jdnd.course3.critter.domain.repository.DayOfWeekRepository;
import com.udacity.jdnd.course3.critter.domain.repository.EmployeeRepository;
import com.udacity.jdnd.course3.critter.domain.repository.SkillRepository;
import com.udacity.jdnd.course3.critter.user.EmployeeDTO;
import com.udacity.jdnd.course3.critter.user.EmployeeMapper;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

  public final EmployeeRepository employeeRepository;

  public final SkillRepository skillRepository;

  public final DayOfWeekRepository dayOfWeekRepository;

  public final EmployeeMapper employeeMapper;

  public EmployeeService(EmployeeRepository employeeRepository, SkillRepository skillRepository,
                         DayOfWeekRepository dayOfWeekRepository, EmployeeMapper employeeMapper) {
    this.employeeRepository = employeeRepository;
    this.skillRepository = skillRepository;
    this.dayOfWeekRepository = dayOfWeekRepository;
    this.employeeMapper = employeeMapper;
  }

  @Transactional
  public EmployeeDTO saveEmployee(EmployeeDTO employeeDTO) {
    Set<Skill> skillList = new HashSet<>();

    for (EmployeeSkillEnum employeeSkillEnum : employeeDTO.getSkills()) {
      skillList.add(skillRepository.findByName(employeeSkillEnum.name()));
    }

    Set<DayOfWeek> dayOfWeeks = new HashSet<>();
    if (employeeDTO.getDaysAvailable() != null) {
      for (java.time.DayOfWeek dayOfWeek : employeeDTO.getDaysAvailable()) {
        dayOfWeeks.add(dayOfWeekRepository.findByName(dayOfWeek.name()));
      }
    }


    Employee employee = new Employee();
    employee.setName(employeeDTO.getName());
    employee.setSkills(skillList);
    employee.setDaysAvailable(dayOfWeeks);
    return employeeMapper.entityToDto(employeeRepository.save(employee));
  }

  public EmployeeDTO getEmployee(Long employeeId) {
    Optional<Employee> employee = employeeRepository.findById(employeeId);
    if (employee.isPresent()) {
      return employeeMapper.entityToDto(employee.get());
    } else {
      throw new RuntimeException();
    }
  }

  @Transactional
  public void setAvailability(Set<java.time.DayOfWeek> daysAvailable, long employeeId) {
    Optional<Employee> employee = employeeRepository.findById(employeeId);
    if (employee.isPresent()) {
      Employee employeeEntity = employee.get();
      Set<DayOfWeek> dayOfWeeks = new HashSet<>();
      for (java.time.DayOfWeek dayOfWeek : daysAvailable) {
        dayOfWeeks.add(dayOfWeekRepository.findByName(dayOfWeek.name()));
      }
      employeeEntity.setDaysAvailable(dayOfWeeks);
      employeeRepository.save(employeeEntity);
    } else {
      throw new RuntimeException();
    }
  }

  public List<EmployeeDTO> findEmployeesForService(Set<EmployeeSkillEnum> skills, LocalDate date) {
    String dayOfWeek = date.getDayOfWeek().name();
    return employeeMapper.entityToDtoList(
        employeeRepository.findAllBySkillAnDate(skills.stream().map(
            Enum::name).collect(
            Collectors.toSet()), dayOfWeek, (long) skills.size()));
  }
}
