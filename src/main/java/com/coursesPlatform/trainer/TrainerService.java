package com.coursesPlatform.trainer;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TrainerService {
    private final TrainerRepository trainerRepository;

    public TrainerService(TrainerRepository trainerRepository) {
        this.trainerRepository = trainerRepository;
    }

    List<TrainerDTO> findAllTrainers() {
        return trainerRepository.findAll()
                .stream()
                .map(this::convertTrainerToDTO)
                .collect(Collectors.toList());
    }

    List<TrainerExternalDTO> showAllTrainersExternal() {
        return trainerRepository.findAll()
                .stream()
                .map(this::convertTrainerToExternalDTO)
                .collect(Collectors.toList());
    }

    List<TrainerDTO> showAllTrainers() {
        return trainerRepository.findAll()
                .stream()
                .map(this::convertTrainerToInternalDTO)
                .collect(Collectors.toList());
    }

    TrainerDTO add(TrainerDTO trainerDTO) {

        Optional<Trainer> titleAndDescription = trainerRepository
                .findByNameAndLastName(trainerDTO.getName(), trainerDTO.getLastName());


        Trainer trainer = trainerRepository.save(convertDTOToTrainer(trainerDTO));
        return convertTrainerToDTO(trainer);
    }

    TrainerDTO update(TrainerDTO updatedTrainer) throws TrainerNotFoundException {
        Optional<Trainer> existingTrainer = trainerRepository
                .findByNameAndLastName(updatedTrainer.getName(), updatedTrainer.getLastName());

        Trainer trainer = existingTrainer.orElseThrow(TrainerNotFoundException::new);
        trainer.update(convertDTOToTrainer(updatedTrainer));
        return updatedTrainer;
    }

    void deleteByNameAndLastName(String name, String lastName) {
        trainerRepository.deleteByNameAndLastName(name, lastName);
    }

    TrainerDTO findByNameAndLastName(String name, String lastName) throws TrainerNotFoundException {
        Optional<Trainer> find = trainerRepository.findByNameAndLastName(name, lastName);
        Trainer trainer = find.orElseThrow(TrainerNotFoundException::new);
        return convertTrainerToDTO(trainer);
    }

    private TrainerDTO convertTrainerToDTO(Trainer trainer) {
        return new TrainerDTO(
                trainer.getName(),
                trainer.getLastName(),
                trainer.getMail(),
                trainer.getPhoneNumber(),
                trainer.getBiography()
        );
    }

    private Trainer convertDTOToTrainer(TrainerDTO trainerDTO) {
        Trainer trainer = new Trainer();
        trainer.setName(trainerDTO.getName());
        trainer.setLastName(trainerDTO.getLastName());
        trainer.setMail(trainerDTO.getMail());
        trainer.setPhoneNumber(trainerDTO.getPhoneNumber());
        trainer.setBiography(trainerDTO.getBiography());

        return trainer;

    }

    private TrainerExternalDTO convertTrainerToExternalDTO(Trainer trainer) {
        return new TrainerExternalDTO(
                trainer.getName(),
                trainer.getLastName(),
                trainer.getBiography()
        );
    }

    private TrainerDTO convertTrainerToInternalDTO(Trainer trainer) {
        return new TrainerDTO(
                trainer.getName(),
                trainer.getLastName(),
                trainer.getMail(),
                trainer.getPhoneNumber(),
                trainer.getBiography()
        );
    }
}

