package com.bprints.be.repositories;

import com.bprints.be.entities.PrintTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrintTagRepository extends JpaRepository<PrintTag, Long> {
}
