package com.bprints.be.repositories;

import com.bprints.be.entities.BluePrint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface BluePrintRepository extends JpaRepository<BluePrint, Long>, JpaSpecificationExecutor<BluePrint> {
}
