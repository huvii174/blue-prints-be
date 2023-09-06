package com.bprints.be.repositories;

import com.bprints.be.entities.BluePrint;
import com.bprints.be.entities.PrintCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<PrintCategory, Long> {
    Optional<PrintCategory> findByIdAndStatus(Long aLong, Boolean status);
    Page<PrintCategory> findByStatus(Pageable pageable, boolean status);
}
