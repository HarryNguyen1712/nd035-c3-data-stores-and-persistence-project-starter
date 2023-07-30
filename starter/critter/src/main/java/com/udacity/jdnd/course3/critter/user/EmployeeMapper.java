package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.common.EmployeeSkillEnum;
import com.udacity.jdnd.course3.critter.domain.entity.Employee;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {

  public EmployeeDTO entityToDto(Employee employee) {
    EmployeeDTO employeeDTO = new EmployeeDTO();
    employeeDTO.setId(employee.getId());
    employeeDTO.setName(employee.getName());
    employeeDTO.setSkills(
        employee.getSkills().stream().map(skill -> EmployeeSkillEnum.valueOf(skill.getName()))
            .collect(
                Collectors.toSet()));
    employeeDTO.setDaysAvailable(employee.getDaysAvailable().stream()
        .map(dayOfWeek -> DayOfWeek.valueOf(dayOfWeek.getName())).collect(
            Collectors.toSet()));
    return employeeDTO;
  }

  public List<EmployeeDTO> entityToDtoList(List<Employee> employees) {
    List<EmployeeDTO> employeeDTOList = new ArrayList<>();
    for (Employee employee : employees) {
      employeeDTOList.add(entityToDto(employee));
    }
    return employeeDTOList;
  }
}
