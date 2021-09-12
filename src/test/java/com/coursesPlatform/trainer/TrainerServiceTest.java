package com.coursesPlatform.trainer;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;
import static java.util.UUID.randomUUID;
import static java.util.stream.LongStream.range;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

@SpringBootTest
@Transactional
public class TrainerServiceTest {

    @Autowired
    private TrainerFacade trainerFacade;

    private TrainerDTO createTrainer(
            String firstName, String lastName, String email, String phoneNumber, String biography
    ) {
        TrainerDTO trainer = new TrainerDTO(firstName, lastName, email, phoneNumber, biography);
        trainerFacade.add(trainer);
        return trainer;
    }

    @Test
    void shouldAddTrainer() {
        //when
        TrainerDTO trainer = createTrainer(
                "Adam", "Dominik", "xyz@gmail.com", "987654321", "jestem przystojny"
        );
        //then
        List<TrainerDTO> allTrainers = trainerFacade.findAllTrainers();
        assertThat(allTrainers).containsExactlyInAnyOrder(trainer);
    }

    @Test
    void shouldThrowExceptionWhenTrainerAlreadyExists() {
        //given
        TrainerDTO trainer1 = createTrainer(
                "Adam", "Dominik", "xyz@gmail.com", "987654321", "jestem przystojny"
        );
        //when
        Throwable thrown = catchThrowable(() -> trainerFacade.add(trainer1));
        //then
        assertThat(thrown).isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("Trainer with given name and lastname already exists");
    }

    @Test
    void shouldShowAllTrainers() {
        //given
        TrainerDTO trainer1 = createTrainer(
                "Adam", "Dominik", "xyz@gmail.com", "987654321", "jestem przystojny"
        );
        TrainerDTO trainer2 = createTrainer(
                "Dawid", "Jamka", "abc@gmail", "123456789", "jestem mega"
        );
        //when
        List<TrainerDTO> allTrainers = trainerFacade.findAllTrainers();
        //then
        assertThat(allTrainers).containsExactlyInAnyOrder(trainer1, trainer2);
    }

    @Test
    public void shouldDeleteTrainer() {
        //given
        TrainerDTO trainer = createTrainer(
                "Adam", "Dominik", "xyz@gmail.com", "987654321", "jestem przystojny"
        );
        //when
        trainerFacade.deleteByNameAndLastName(trainer.getName(), trainer.getLastName());
        //then
        List<TrainerDTO> allTrainers = trainerFacade.findAllTrainers();
        assertThat(allTrainers).isEmpty();
    }

    @Test
    public void shouldUpdateTrainer() throws TrainerNotFoundException {
        //given
        TrainerDTO trainer = createTrainer(
                "Adam", "Dominik", "xyz@gmail.com", "987654321", "jestem przystojny"
        );
        TrainerDTO newTrainer = new TrainerDTO(
                "Dawid", "Jamka", "abc@gmail.com", "123456789", "jestem miły"
        );
        //when
        TrainerDTO updatedTrainer = trainerFacade.update(
                trainer.getName(), trainer.getLastName(), newTrainer
        );
        //then
        List<TrainerDTO> allTrainers = trainerFacade.findAllTrainers();

        assertThat(allTrainers).containsExactlyInAnyOrder(updatedTrainer);
    }

    @Test
    public void shouldThrowExceptionWhenUpdatingNonExistingTrainer() {
        //given
        String randomName = randomUUID().toString();
        String randomLastname = randomUUID().toString();
        TrainerDTO newTrainer = new TrainerDTO(
                "Dawid", "Jamka", "abc@gmail.com", "987654321", "jestem miły");
        //when
        Throwable thrown = catchThrowable(() -> trainerFacade.update(
                randomName, randomLastname, newTrainer
        ));
        //then
        assertThat(thrown).isInstanceOf(TrainerNotFoundException.class);
    }

    @Test
    public void shouldReturnAllTrainersAsExternalDTOS() {
        //given
        TrainerDTO trainer1 = createTrainer(
                "Adam", "Dominik", "xyz@gmail.com", "987654321", "jestem przystojny"
        );
        TrainerDTO trainer2 = createTrainer(
                "Dawid", "Jamka", "abc@gmail", "123456789", "jestem mega"
        );
        //when
        List<TrainerExternalDTO> trainerExternalDTOS = trainerFacade.showAllTrainersExternal();
        //then
        assertThat(trainerExternalDTOS.size()).isEqualTo(2);
        TrainerExternalDTO firstExternal = trainerExternalDTOS.get(0);
        assertThat(firstExternal.getName()).isEqualTo(trainer1.getName());
        assertThat(firstExternal.getLastName()).isEqualTo(trainer1.getLastName());
        assertThat(firstExternal.getBiography()).isEqualTo(trainer1.getBiography());
        TrainerExternalDTO secondExternal = trainerExternalDTOS.get(1);
        assertThat(secondExternal.getName()).isEqualTo(trainer2.getName());
        assertThat(secondExternal.getLastName()).isEqualTo(trainer2.getLastName());
        assertThat(secondExternal.getBiography()).isEqualTo(trainer2.getBiography());
    }

    @Test
    void shouldAddVacationToTrainer() {
        //given
        TrainerDTO trainer1 = createTrainer(
                "Adam", "Dominik", "xyz@gmail.com", "987654321", "jestem przystojny"
        );
        LocalDate vacationStart = LocalDate.of(2021, 9, 11);
        LocalDate vacationEnd = LocalDate.of(2021, 9, 13);
        //when
        trainerFacade.addVacationToTrainer(
                trainer1.getName(),
                trainer1.getLastName(),
                vacationStart,
                vacationEnd
        );
        //then
        Trainer trainerEntity = trainerFacade.
                findTrainerEntity(trainer1.getName(), trainer1.getLastName());
        long numOfDays = DAYS.between(vacationStart, vacationEnd);
        LocalDate[] localDates = range(0, numOfDays + 1)
                .mapToObj(vacationStart::plusDays)
                .toArray(LocalDate[]::new);
        assertThat(trainerEntity.getVacations()).containsExactlyInAnyOrder(localDates);
    }

    @Test
    void shouldThrowExceptionWhenEnteredVacationPeriodIsTooLong() {
        //given
        TrainerDTO trainer1 = createTrainer(
                "Adam", "Dominik", "xyz@gmail.com", "987654321", "jestem przystojny"
        );
        LocalDate vacationStart = LocalDate.of(2021, 9, 1);
        LocalDate vacationEnd = LocalDate.of(2021, 9, 30);
        //when
        Throwable thrown = catchThrowable(() -> trainerFacade.addVacationToTrainer(
                trainer1.getName(),
                trainer1.getLastName(),
                vacationStart,
                vacationEnd)
        );
        //then
        assertThat(thrown).isInstanceOf(VacationTooLongException.class);
        Trainer trainerEntity = trainerFacade.
                findTrainerEntity(trainer1.getName(), trainer1.getLastName());
        assertThat(trainerEntity.getVacations()).isEmpty();
    }
}



