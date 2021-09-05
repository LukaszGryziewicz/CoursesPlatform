package com.coursesPlatform.trainerHR;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TrainerHrFacade {

    private final TrainerHrService trainerService;

    public TrainerHrFacade(TrainerHrService trainerService) {
        this.trainerService = trainerService;
    }

    List<TrainerHrDTO> findAllTrainers() {
        return trainerService.findAllTrainers();
    }

    List<TrainerHrExternalDTO> showAllTrainersExternal() {
        return trainerService.showAllTrainersExternal();
    }

    public TrainerHrDTO add(TrainerHrDTO trainerDTO) {
        return trainerService.add(trainerDTO);
    }

    TrainerHrDTO update(String firstname, String lastname, TrainerHrDTO updatedTrainer) {
        return trainerService.update(firstname, lastname, updatedTrainer);
    }

    void deleteByNameAndLastName(String name, String lastName) {
        trainerService.deleteByNameAndLastName(name, lastName);
    }

    TrainerHrDTO findByNameAndLastName(String name, String lastName) {
        return trainerService.findByNameAndLastName(name, lastName);
    }
}
