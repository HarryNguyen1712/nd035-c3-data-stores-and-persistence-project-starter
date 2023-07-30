package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.common.EmployeeSkillEnum;
import com.udacity.jdnd.course3.critter.domain.entity.Employee;
import com.udacity.jdnd.course3.critter.domain.entity.Pet;
import com.udacity.jdnd.course3.critter.domain.entity.Schedule;
import com.udacity.jdnd.course3.critter.domain.entity.Skill;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.stereotype.Component;

@Component
public class ScheduleMapper {

  public ScheduleDTO entityToDto(Schedule schedule) {
    ScheduleDTO scheduleDTO = new ScheduleDTO();
    scheduleDTO.setId(schedule.getId());
    scheduleDTO.setDate(schedule.getDate());

    Set<EmployeeSkillEnum> employeeSkillEnums = new HashSet<>();
    for (Skill skill : schedule.getActivities()) {
      employeeSkillEnums.add(EmployeeSkillEnum.valueOf(skill.getName()));
    }
    scheduleDTO.setActivities(employeeSkillEnums);

    List<Long> employeeIds = new ArrayList<>();
    for (Employee employee : schedule.getEmployees()) {
      employeeIds.add(employee.getId());
    }
    scheduleDTO.setEmployeeIds(employeeIds);
    List<Long> petIds = new ArrayList<>();
    for (Pet pet : schedule.getPets()) {
      petIds.add(pet.getId());
    }
    scheduleDTO.setPetIds(petIds);
    return scheduleDTO;
  }

  public List<ScheduleDTO> entityToDtoList(List<Schedule> schedules) {
    List<ScheduleDTO> scheduleDTOList = new ArrayList<>();
    for (Schedule schedule : schedules) {
      scheduleDTOList.add(entityToDto(schedule));
    }
    return scheduleDTOList;
  }
}
