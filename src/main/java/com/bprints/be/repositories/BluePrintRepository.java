package com.bprints.be.repositories;

import com.bprints.be.entities.BluePrint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BluePrintRepository extends JpaRepository<BluePrint, Long>, JpaSpecificationExecutor<BluePrint> {
    Optional<BluePrint> findByIdAndStatus(Long aLong, Boolean status);
}
