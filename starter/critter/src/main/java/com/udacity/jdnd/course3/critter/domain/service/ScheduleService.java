package com.udacity.jdnd.course3.critter.domain.service;

import com.udacity.jdnd.course3.critter.common.EmployeeSkillEnum;
import com.udacity.jdnd.course3.critter.domain.entity.Employee;
import com.udacity.jdnd.course3.critter.domain.entity.Pet;
import com.udacity.jdnd.course3.critter.domain.entity.Schedule;
import com.udacity.jdnd.course3.critter.domain.entity.Skill;
import com.udacity.jdnd.course3.critter.domain.repository.EmployeeRepository;
import com.udacity.jdnd.course3.critter.domain.repository.PetRepository;
import com.udacity.jdnd.course3.critter.domain.repository.ScheduleRepository;
import com.udacity.jdnd.course3.critter.domain.repository.SkillRepository;
import com.udacity.jdnd.course3.critter.schedule.ScheduleDTO;
import com.udacity.jdnd.course3.critter.schedule.ScheduleMapper;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class ScheduleService {

  private final ScheduleRepository scheduleRepository;

  private final EmployeeRepository employeeRepository;

  private final SkillRepository skillRepository;

  private final PetRepository petRepository;

  private final ScheduleMapper scheduleMapper;

  public ScheduleService(ScheduleRepository scheduleRepository,
                         EmployeeRepository employeeRepository, SkillRepository skillRepository,
                         PetRepository petRepository, ScheduleMapper scheduleMapper) {
    this.scheduleRepository = scheduleRepository;
    this.employeeRepository = employeeRepository;
    this.skillRepository = skillRepository;
    this.petRepository = petRepository;
    this.scheduleMapper = scheduleMapper;
  }

  @Transactional
  public ScheduleDTO saveSchedule(ScheduleDTO scheduleDTO) {
    Schedule schedule = new Schedule();

    Set<Employee> employeeSet = new HashSet<>();

    for (Long i : scheduleDTO.getEmployeeIds()) {
      Optional<Employee> employee = employeeRepository.findById(i);
      if (employee.isPresent()) {
        Employee employee1 = employee.get();
        employee1.setDaysAvailable(null);
        employeeSet.add(employee1);
      } else {
        throw new RuntimeException();
      }
    }

    Set<Skill> skillList = new HashSet<>();

    for (EmployeeSkillEnum employeeSkillEnum : scheduleDTO.getActivities()) {

      skillList.add(skillRepository.findByName(employeeSkillEnum.name()));
    }

    Set<Pet> pets = new HashSet<>();

    for (Long aLong : scheduleDTO.getPetIds()) {
      Optional<Pet> pet = petRepository.findById(aLong);
      if (pet.isPresent()) {
        pets.add(pet.get());
      } else {
        throw new RuntimeException();
      }
    }
    schedule.setEmployees(employeeSet);
    schedule.setActivities(skillList);
    schedule.setPets(pets);
    schedule.setDate(scheduleDTO.getDate());
    return scheduleMapper.entityToDto(scheduleRepository.save(schedule));
  }

  public List<ScheduleDTO> getAllSchedule() {
    return scheduleMapper.entityToDtoList(scheduleRepository.findAll());
  }

  public List<ScheduleDTO> getScheduleForPet(Long petId) {
    return scheduleMapper.entityToDtoList(scheduleRepository.getSchedulesByPetID(petId));
  }

  public List<ScheduleDTO> getScheduleForEmployee(Long employeeId) {
    return scheduleMapper.entityToDtoList(scheduleRepository.getSchedulesByEmployeeId(employeeId));
  }

  public List<ScheduleDTO> getScheduleForCustomer(Long employeeId) {
    return scheduleMapper.entityToDtoList(scheduleRepository.getSchedulesByCustomerId(employeeId));
  }
}
