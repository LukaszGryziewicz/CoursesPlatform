package com.coursesPlatform.coursePortfolio;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;
import static java.util.Collections.disjoint;
import static java.util.stream.Collectors.toList;
import static java.util.stream.LongStream.range;

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

    public Trainer findAvailableTrainer(LocalDate orderStartDate, LocalDate orderEndDate) {
        List<LocalDate> orderDates = getDaysBetween(orderStartDate, orderEndDate);
        List<Trainer> collect = trainerRepository.findAll().stream()
                .filter(trainer -> disjoint(orderDates, trainer.getUnavailableDays()))
                .collect(toList());
        return collect.get(0);
    }

    private List<LocalDate> getDaysBetween(LocalDate orderStartDate, LocalDate orderEndDate) {
        long numOfDays = DAYS.between(orderStartDate, orderEndDate);
        return range(0, numOfDays)
                .mapToObj(orderStartDate::plusDays)
                .collect(toList());
    }

    private TrainerDTO convertTrainerToDTO(Trainer trainer) {
        return new TrainerDTO(
                trainer.getName(),
                trainer.getLastName(),
                trainer.getMail(),
                trainer.getUnavailableDays()
        );
    }

    private Trainer convertDTOToTrainer(TrainerDTO trainerDTO) {
        return new Trainer(
                trainerDTO.getName(),
                trainerDTO.getLastName(),
                trainerDTO.getMail(),
                trainerDTO.getUnavailableDays()
        );
    }
}
