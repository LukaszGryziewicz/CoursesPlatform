package com.coursesPlatform.coursePortfolio;

import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
class TrainerService {
    private final TrainerRepository trainerRepository;

    TrainerService(TrainerRepository trainerRepository) {
        this.trainerRepository = trainerRepository;
    }

    TrainerDTO add(String name, String lastName, String email) {
        TrainerDTO trainerDTO = new TrainerDTO(name, lastName, email);
        Trainer savedTrainer = trainerRepository.save(convertDTOToTrainer(trainerDTO));
        return convertTrainerToDTO(savedTrainer);
    }

    List<TrainerDTO> findAll() {
        return trainerRepository.findAll().stream()
                .map(this::convertTrainerToDTO)
                .collect(toList());
    }

    private TrainerDTO convertTrainerToDTO(Trainer trainer) {
        return new TrainerDTO(
                trainer.getName(),
                trainer.getLastName(),
                trainer.getMail()
        );
    }

    private Trainer convertDTOToTrainer(TrainerDTO trainerDTO) {
        return new Trainer(
                trainerDTO.getName(),
                trainerDTO.getLastName(),
                trainerDTO.getMail()
        );
    }
}
