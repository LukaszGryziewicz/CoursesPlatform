package com.coursesPlatform.coursePortfolio;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TrainerRepository extends JpaRepository<Trainer, Long> {
    Optional<Trainer> findByNameAndLastName(String name, String lastName);

    void deleteByNameAndLastName(String name, String lastName);
}
