package com.udacity.jdnd.course3.critter.domain.repository;

import com.udacity.jdnd.course3.critter.domain.entity.PetType;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetTypeRepository extends JpaRepository<PetType, Long> {
  Optional<PetType> findByName(String name);
}
