package com.bprints.be.repositories;

import com.bprints.be.entities.PrintCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<PrintCategory, Long> {
}
