package com.udacity.jdnd.course3.critter.domain.repository;

import com.udacity.jdnd.course3.critter.domain.entity.Schedule;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
  @Query(value = "select s from Schedule s join s.pets p where p.id = :petId")
  List<Schedule> getSchedulesByPetID(Long petId);

  @Query(value = "select s from Schedule s join s.employees e where e.id = :employeeId")
  List<Schedule> getSchedulesByEmployeeId(Long employeeId);

  @Query(value = "select s from Schedule s join s.pets p where p.customer.id = :customerId")
  List<Schedule> getSchedulesByCustomerId(Long customerId);
}
