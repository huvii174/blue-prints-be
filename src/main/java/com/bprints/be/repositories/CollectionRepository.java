package com.bprints.be.repositories;

import com.bprints.be.entities.PrintCollection;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CollectionRepository extends JpaRepository<PrintCollection, Long> {
    Optional<PrintCollection> findByIdAndStatus(Long aLong, Boolean status);
    Page<PrintCollection> findByStatus(Pageable pageable, boolean status);
}
