package com.coursesPlatform.coursePortfolio;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

interface TrainerProjectionRepository extends JpaRepository<TrainerProjection, Long> {

    Optional<TrainerProjection> findByNameAndLastName(String name, String lastName);
}
