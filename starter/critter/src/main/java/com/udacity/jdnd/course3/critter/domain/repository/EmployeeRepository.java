package com.udacity.jdnd.course3.critter.domain.repository;

import com.udacity.jdnd.course3.critter.domain.entity.Employee;
import java.util.List;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query(value = "select distinct e from Employee e join e.skills s join e.daysAvailable d where s.name in :skills and d.name = :dayOfWeek group by e.id having count (e.id) =:skillsCount")
  List<Employee> findAllBySkillAnDate(Set<String> skills, String dayOfWeek, Long skillsCount);
}
