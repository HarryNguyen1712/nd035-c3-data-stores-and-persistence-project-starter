package com.udacity.jdnd.course3.critter.domain.repository;

import com.udacity.jdnd.course3.critter.domain.entity.DayOfWeek;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DayOfWeekRepository extends JpaRepository<DayOfWeek, Long> {
  DayOfWeek findByName(String name);
}
