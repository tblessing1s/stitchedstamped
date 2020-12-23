package com.stitchedstamed.repositories;

import com.stitchedstamed.models.Monogram;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "monograms", path = "monograms")
public interface MonogramRepository extends JpaRepository<Monogram, Long> {
}
