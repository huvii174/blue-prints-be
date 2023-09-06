package com.bprints.be.repositories;

import com.bprints.be.entities.DesignStyle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DesignStyleRepository extends JpaRepository<DesignStyle, Long> {
}
