package com.coursesPlatform.trainer;

import com.coursesPlatform.coursePortfolio.OrderFacade;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.time.temporal.ChronoUnit.DAYS;
import static java.util.stream.Collectors.toList;
import static java.util.stream.LongStream.range;

@Service
public
class TrainerService {
    private final TrainerRepository trainerRepository;
    private final OrderFacade orderFacade;
    private final int vacationDaysLimit;

    TrainerService(TrainerRepository trainerRepository,
                   OrderFacade orderFacade,
                   @Value("${vacation.length.limit}") int vacationDaysLimit) {
        this.trainerRepository = trainerRepository;
        this.orderFacade = orderFacade;
        this.vacationDaysLimit = vacationDaysLimit;
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

    TrainerDTO add(TrainerDTO trainerDTO) {
        Optional<Trainer> byNameAndLastName = trainerRepository
                .findByNameAndLastName(trainerDTO.getName(), trainerDTO.getLastName());
        if ( byNameAndLastName.isPresent() ) {
            throw new IllegalStateException("Trainer with given name and lastname already exists");
        }
        Trainer trainer = trainerRepository.save(convertDTOToTrainer(trainerDTO));
        orderFacade.trainerCreated(trainer.getName(), trainer.getLastName(), trainer.getMail());
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

    boolean checkIfTrainerIsOnVacation(String name, String lastName, LocalDate vacationStart, LocalDate vacationEnd) {
        Trainer trainer = convertDTOToTrainer(findByNameAndLastName(name, lastName));
        List<LocalDate> daysBetween = getDaysBetween(vacationStart, vacationEnd.plusDays(1));
        return trainer.getVacations().stream()
                .anyMatch(daysBetween::contains);
    }

    void addVacationToTrainer(String name, String lastName,
                              LocalDate vacationStart, LocalDate vacationEnd) {
        Trainer trainer = findTrainer(name, lastName);
        List<LocalDate> daysBetween = getDaysBetween(vacationStart, vacationEnd.plusDays(1));
        if ( trainer.getVacations().size() + daysBetween.size() > vacationDaysLimit ) {
            throw new VacationTooLongException();
        }
        trainer.addVacation(daysBetween);
        orderFacade.vacationRegistered(name, lastName, daysBetween);
        trainerRepository.save(trainer);
    }

    TrainerDTO findByNameAndLastName(String name, String lastName) {
        Optional<Trainer> find = trainerRepository.findByNameAndLastName(name, lastName);
        Trainer trainer = find.orElseThrow(TrainerNotFoundException::new);
        return convertTrainerToDTO(trainer);
    }

    Trainer findTrainer(String name, String lastName) {
        return trainerRepository
                .findByNameAndLastName(name, lastName)
                .orElseThrow(TrainerNotFoundException::new);
    }

    private List<LocalDate> getDaysBetween(LocalDate orderStartDate, LocalDate orderEndDate) {
        long numOfDays = DAYS.between(orderStartDate, orderEndDate.plusDays(1));
        return range(0, numOfDays)
                .mapToObj(orderStartDate::plusDays)
                .collect(toList());
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

