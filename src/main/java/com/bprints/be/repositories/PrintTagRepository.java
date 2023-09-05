package com.bprints.be.repositories;

import com.bprints.be.entities.PrintTag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrintTagRepository extends JpaRepository<PrintTag, Long> {
}
