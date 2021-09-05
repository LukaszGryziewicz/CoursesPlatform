package com.coursesPlatform.trainer;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TrainerFacade {

    private final TrainerService trainerService;

    public TrainerFacade(TrainerService trainerService) {
        this.trainerService = trainerService;
    }

    List<TrainerDTO> findAllTrainers() {
        return trainerService.findAllTrainers();
    }

    List<TrainerExternalDTO> showAllTrainersExternal() {
        return trainerService.showAllTrainersExternal();
    }

    TrainerDTO add(TrainerDTO trainerDTO) {
        return trainerService.add(trainerDTO);
    }

    TrainerDTO update(String firstname, String lastname, TrainerDTO updatedTrainer) {
        return trainerService.update(firstname, lastname, updatedTrainer);
    }

    void deleteByNameAndLastName(String name, String lastName) {
        trainerService.deleteByNameAndLastName(name, lastName);
    }

    TrainerDTO findByNameAndLastName(String name, String lastName) {
        return trainerService.findByNameAndLastName(name, lastName);
    }
}
