package com.coursesPlatform.coursePortfolio;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;
import static java.util.Collections.disjoint;
import static java.util.stream.Collectors.toList;
import static java.util.stream.LongStream.range;

@Service
class TrainerProjectionService {
    private final TrainerProjectionRepository trainerRepository;

    TrainerProjectionService(TrainerProjectionRepository trainerRepository) {
        this.trainerRepository = trainerRepository;
    }

    TrainerProjectionDTO add(String name, String lastName, String email) {
        TrainerProjectionDTO trainerDTO = new TrainerProjectionDTO(name, lastName, email);
        TrainerProjection savedTrainer = trainerRepository.save(convertDTOToTrainer(trainerDTO));
        return convertTrainerToDTO(savedTrainer);
    }

    List<TrainerProjectionDTO> findAll() {
        return trainerRepository.findAll().stream()
                .map(this::convertTrainerToDTO)
                .collect(toList());
    }

    public TrainerProjection findAvailableTrainer(LocalDate orderStartDate, LocalDate orderEndDate) {
        List<LocalDate> orderDates = getDaysBetween(orderStartDate, orderEndDate);
        List<TrainerProjection> collect = trainerRepository.findAll().stream()
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

    private TrainerProjectionDTO convertTrainerToDTO(TrainerProjection trainer) {
        return new TrainerProjectionDTO(
                trainer.getName(),
                trainer.getLastName(),
                trainer.getMail(),
                trainer.getUnavailableDays()
        );
    }

    private TrainerProjection convertDTOToTrainer(TrainerProjectionDTO trainerDTO) {
        return new TrainerProjection(
                trainerDTO.getName(),
                trainerDTO.getLastName(),
                trainerDTO.getMail(),
                trainerDTO.getUnavailableDays()
        );
    }
}
