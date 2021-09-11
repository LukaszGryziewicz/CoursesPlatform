package com.coursesPlatform.coursePortfolio;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderFacade {
    private final TrainerProjectionService trainerService;

    public OrderFacade(TrainerProjectionService trainerService) {
        this.trainerService = trainerService;
    }

    public TrainerProjectionDTO add(String name, String lastName, String email) {
        return trainerService.add(name, lastName, email);
    }

    List<TrainerProjectionDTO> findAll() {
        return trainerService.findAll();
    }
}
