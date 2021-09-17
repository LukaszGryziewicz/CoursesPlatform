package com.coursesPlatform.trainer;

import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.Optional;

interface TrainerRepository extends JpaRepository<Trainer, Long> {
    Optional<Trainer> findByNameAndLastName(String name, String lastName);

    @Transactional
    void deleteByNameAndLastName(String name, String lastName);
}
