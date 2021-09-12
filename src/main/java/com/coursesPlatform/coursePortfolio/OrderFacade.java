package com.coursesPlatform.coursePortfolio;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class OrderFacade {
    private final TrainerProjectionService trainerService;

    public OrderFacade(TrainerProjectionService trainerService) {
        this.trainerService = trainerService;
    }

    public TrainerProjectionDTO trainerCreated(String name, String lastName, String email) {
        return trainerService.trainerCreated(name, lastName, email);
    }

    public void vacationRegistered(String name, String lastName, List<LocalDate> vacationDays) {
        trainerService.vacationRegistered(name, lastName, vacationDays);
    }

    List<TrainerProjectionDTO> findAll() {
        return trainerService.findAll();
    }
}
