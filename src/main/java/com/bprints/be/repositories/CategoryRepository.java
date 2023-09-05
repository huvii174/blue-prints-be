package com.bprints.be.repositories;

import com.bprints.be.entities.PrintCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<PrintCategory, Long> {
}
