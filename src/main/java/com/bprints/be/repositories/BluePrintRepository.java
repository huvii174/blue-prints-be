package com.bprints.be.repositories;

import com.bprints.be.entities.BluePrint;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BluePrintRepository extends JpaRepository<BluePrint, Long> {
}
