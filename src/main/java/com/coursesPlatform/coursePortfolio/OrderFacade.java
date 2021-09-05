package com.coursesPlatform.coursePortfolio;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderFacade {
    private final TrainerService trainerService;

    public OrderFacade(TrainerService trainerService) {
        this.trainerService = trainerService;
    }

    public TrainerDTO add(String name, String lastName, String email) {
        return trainerService.add(name, lastName, email);
    }

    List<TrainerDTO> findAll() {
        return trainerService.findAll();
    }
}
