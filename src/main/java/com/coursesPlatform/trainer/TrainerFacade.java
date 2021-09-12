package com.coursesPlatform.trainer;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
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

    public TrainerDTO add(TrainerDTO trainerDTO) {
        return trainerService.add(trainerDTO);
    }

    TrainerDTO update(String firstname, String lastname, TrainerDTO updatedTrainer) {
        return trainerService.update(firstname, lastname, updatedTrainer);
    }

    void deleteByNameAndLastName(String name, String lastName) {
        trainerService.deleteByNameAndLastName(name, lastName);
    }

    public void addVacationToTrainer(String name, String lastName, LocalDate vacationStart, LocalDate vacationEnd) {
        trainerService.addVacationToTrainer(name, lastName, vacationStart, vacationEnd);
    }

    Trainer findTrainerEntity(String name, String lastName) {
        return trainerService.findTrainer(name, lastName);
    }
}
