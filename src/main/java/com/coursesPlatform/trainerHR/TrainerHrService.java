package com.coursesPlatform.trainerHR;

import com.coursesPlatform.coursePortfolio.OrderFacade;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
class TrainerHrService {
    private final TrainerHrRepository trainerRepository;
    private final OrderFacade orderFacade;

    TrainerHrService(TrainerHrRepository trainerRepository, OrderFacade orderFacade) {
        this.trainerRepository = trainerRepository;
        this.orderFacade = orderFacade;
    }

    List<TrainerHrDTO> findAllTrainers() {
        return trainerRepository.findAll()
                .stream()
                .map(this::convertTrainerToDTO)
                .collect(Collectors.toList());
    }

    List<TrainerHrExternalDTO> showAllTrainersExternal() {
        return trainerRepository.findAll()
                .stream()
                .map(this::convertTrainerToExternalDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    TrainerHrDTO add(TrainerHrDTO trainerDTO) {
        Optional<TrainerHR> byNameAndLastName = trainerRepository
                .findByNameAndLastName(trainerDTO.getName(), trainerDTO.getLastName());
        if ( byNameAndLastName.isPresent() ) {
            throw new IllegalStateException("Trainer with given name and lastname already exists");
        }
        TrainerHR trainer = trainerRepository.save(convertDTOToTrainer(trainerDTO));
        orderFacade.add(trainer.getName(), trainer.getLastName(), trainer.getMail());
        return convertTrainerToDTO(trainer);
    }

    TrainerHrDTO update(String firstname, String lastname, TrainerHrDTO updatedTrainer) {
        Optional<TrainerHR> existingTrainer = trainerRepository
                .findByNameAndLastName(firstname, lastname);
        TrainerHR trainer = existingTrainer.orElseThrow(TrainerNotFoundException::new);
        trainer.update(convertDTOToTrainer(updatedTrainer));
        TrainerHR savedTrainer = trainerRepository.save(trainer);
        return convertTrainerToDTO(savedTrainer);
    }

    void deleteByNameAndLastName(String name, String lastName) {
        trainerRepository.deleteByNameAndLastName(name, lastName);
    }

    TrainerHrDTO findByNameAndLastName(String name, String lastName) {
        Optional<TrainerHR> find = trainerRepository.findByNameAndLastName(name, lastName);
        TrainerHR trainer = find.orElseThrow(TrainerNotFoundException::new);
        return convertTrainerToDTO(trainer);
    }

    private TrainerHrDTO convertTrainerToDTO(TrainerHR trainer) {
        return new TrainerHrDTO(
                trainer.getName(),
                trainer.getLastName(),
                trainer.getMail(),
                trainer.getPhoneNumber(),
                trainer.getBiography()
        );
    }

    private TrainerHR convertDTOToTrainer(TrainerHrDTO trainerDTO) {
        TrainerHR trainer = new TrainerHR();
        trainer.setName(trainerDTO.getName());
        trainer.setLastName(trainerDTO.getLastName());
        trainer.setMail(trainerDTO.getMail());
        trainer.setPhoneNumber(trainerDTO.getPhoneNumber());
        trainer.setBiography(trainerDTO.getBiography());
        return trainer;
    }

    private TrainerHrExternalDTO convertTrainerToExternalDTO(TrainerHR trainer) {
        return new TrainerHrExternalDTO(
                trainer.getName(),
                trainer.getLastName(),
                trainer.getBiography()
        );
    }
}

