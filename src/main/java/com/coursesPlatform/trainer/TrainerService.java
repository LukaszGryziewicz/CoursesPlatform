package com.coursesPlatform.trainer;

import com.coursesPlatform.coursePortfolio.OrderFacade;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public
class TrainerService {
    private final TrainerRepository trainerRepository;
    private final OrderFacade orderFacade;

    TrainerService(TrainerRepository trainerRepository, OrderFacade orderFacade) {
        this.trainerRepository = trainerRepository;
        this.orderFacade = orderFacade;
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

    @Transactional
    TrainerDTO add(TrainerDTO trainerDTO) {
        Optional<Trainer> byNameAndLastName = trainerRepository
                .findByNameAndLastName(trainerDTO.getName(), trainerDTO.getLastName());
        if ( byNameAndLastName.isPresent() ) {
            throw new IllegalStateException("Trainer with given name and lastname already exists");
        }
        Trainer trainer = trainerRepository.save(convertDTOToTrainer(trainerDTO));
        orderFacade.add(trainer.getName(), trainer.getLastName(), trainer.getMail());
        return convertTrainerToDTO(trainer);
    }

    TrainerDTO update(String firstname, String lastname, TrainerDTO updatedTrainer) {
        Optional<Trainer> existingTrainer = trainerRepository
                .findByNameAndLastName(firstname, lastname);
        Trainer trainer = existingTrainer.orElseThrow(TrainerNotFoundException::new);
        trainer.update(convertDTOToTrainer(updatedTrainer));
        Trainer savedTrainer = trainerRepository.save(trainer);
        return convertTrainerToDTO(savedTrainer);
    }

    void deleteByNameAndLastName(String name, String lastName) {
        trainerRepository.deleteByNameAndLastName(name, lastName);
    }

    TrainerDTO findByNameAndLastName(String name, String lastName) {
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
}

