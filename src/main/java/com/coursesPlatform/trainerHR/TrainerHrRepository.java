package com.coursesPlatform.trainerHR;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

interface TrainerHrRepository extends JpaRepository<TrainerHR, Long> {
    Optional<TrainerHR> findByNameAndLastName(String name, String lastName);

    void deleteByNameAndLastName(String name, String lastName);
}
