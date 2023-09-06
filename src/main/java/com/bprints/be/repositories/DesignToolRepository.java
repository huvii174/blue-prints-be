package com.bprints.be.repositories;

import com.bprints.be.entities.DesignTool;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DesignToolRepository extends JpaRepository<DesignTool, Long> {
}
