package com.bprints.be.repositories;

import com.bprints.be.entities.DesignStyle;
import com.bprints.be.entities.DesignTool;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DesignToolRepository extends JpaRepository<DesignTool, Long> {
    Optional<DesignTool> findByIdAndStatus(Long aLong, Boolean status);
    Page<DesignTool> findByStatus(Pageable pageable, boolean status);
}
