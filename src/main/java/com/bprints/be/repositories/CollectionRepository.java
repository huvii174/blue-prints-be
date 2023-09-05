package com.bprints.be.repositories;

import com.bprints.be.entities.PrintCollection;
import org.hibernate.mapping.Collection;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CollectionRepository extends JpaRepository<PrintCollection, Long> {
}
