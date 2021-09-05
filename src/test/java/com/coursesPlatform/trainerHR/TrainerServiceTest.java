package com.coursesPlatform.trainerHR;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.List;

import static java.util.UUID.randomUUID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

@SpringBootTest
@Transactional
public class TrainerServiceTest {

    @Autowired
    private TrainerHrFacade trainerHrFacade;

    private TrainerHrDTO createTrainer(
            String firstName, String lastName, String email, String phoneNumber, String biography
    ) {
        TrainerHrDTO trainer = new TrainerHrDTO(firstName, lastName, email, phoneNumber, biography);
        trainerHrFacade.add(trainer);
        return trainer;
    }

    @Test
    void shouldAddTrainer() {
        //when
        TrainerHrDTO trainer = createTrainer(
                "Adam", "Dominik", "xyz@gmail.com", "987654321", "jestem przystojny"
        );
        //then
        List<TrainerHrDTO> allTrainers = trainerHrFacade.findAllTrainers();
        assertThat(allTrainers).containsExactlyInAnyOrder(trainer);
    }

    @Test
    void shouldThrowExceptionWhenTrainerAlreadyExists() {
        //given
        TrainerHrDTO trainer1 = createTrainer(
                "Adam", "Dominik", "xyz@gmail.com", "987654321", "jestem przystojny"
        );
        //when
        Throwable thrown = catchThrowable(() -> trainerHrFacade.add(trainer1));
        //then
        assertThat(thrown).isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("Trainer with given name and lastname already exists");
    }

    @Test
    void shouldShowAllTrainers() {
        //given
        TrainerHrDTO trainer1 = createTrainer(
                "Adam", "Dominik", "xyz@gmail.com", "987654321", "jestem przystojny"
        );
        TrainerHrDTO trainer2 = createTrainer(
                "Dawid", "Jamka", "abc@gmail", "123456789", "jestem mega"
        );
        //when
        List<TrainerHrDTO> allTrainers = trainerHrFacade.findAllTrainers();
        //then
        assertThat(allTrainers).containsExactlyInAnyOrder(trainer1, trainer2);
    }

    @Test
    public void shouldDeleteTrainer() {
        //given
        TrainerHrDTO trainer = createTrainer(
                "Adam", "Dominik", "xyz@gmail.com", "987654321", "jestem przystojny"
        );
        //when
        trainerHrFacade.deleteByNameAndLastName(trainer.getName(), trainer.getLastName());
        //then
        List<TrainerHrDTO> allTrainers = trainerHrFacade.findAllTrainers();
        assertThat(allTrainers).isEmpty();
    }

    @Test
    public void shouldUpdateTrainer() throws TrainerNotFoundException {
        //given
        TrainerHrDTO trainer = createTrainer(
                "Adam", "Dominik", "xyz@gmail.com", "987654321", "jestem przystojny"
        );
        TrainerHrDTO newTrainer = new TrainerHrDTO(
                "Dawid", "Jamka", "abc@gmail.com", "123456789", "jestem miły"
        );
        //when
        TrainerHrDTO updatedTrainer = trainerHrFacade.update(
                trainer.getName(), trainer.getLastName(), newTrainer
        );
        //then
        List<TrainerHrDTO> allTrainers = trainerHrFacade.findAllTrainers();
        assertThat(allTrainers).containsExactlyInAnyOrder(updatedTrainer);
    }

    @Test
    public void shouldThrowExceptionWhenUpdatingNonExistingTrainer() {
        //given
        String randomName = randomUUID().toString();
        String randomLastname = randomUUID().toString();
        TrainerHrDTO newTrainer = new TrainerHrDTO(
                "Dawid", "Jamka", "abc@gmail.com", "987654321", "jestem miły");
        //when
        Throwable thrown = catchThrowable(() -> trainerHrFacade.update(
                randomName, randomLastname, newTrainer
        ));
        //then
        assertThat(thrown).isInstanceOf(TrainerNotFoundException.class);
    }

    @Test
    public void shouldReturnAllTrainersAsExternalDTOS() {
        //given
        TrainerHrDTO trainer1 = createTrainer(
                "Adam", "Dominik", "xyz@gmail.com", "987654321", "jestem przystojny"
        );
        TrainerHrDTO trainer2 = createTrainer(
                "Dawid", "Jamka", "abc@gmail", "123456789", "jestem mega"
        );
        //when
        List<TrainerHrExternalDTO> trainerExternalDTOS = trainerHrFacade.showAllTrainersExternal();
        //then
        assertThat(trainerExternalDTOS.size()).isEqualTo(2);
        TrainerHrExternalDTO firstExternal = trainerExternalDTOS.get(0);
        assertThat(firstExternal.getName()).isEqualTo(trainer1.getName());
        assertThat(firstExternal.getLastName()).isEqualTo(trainer1.getLastName());
        assertThat(firstExternal.getBiography()).isEqualTo(trainer1.getBiography());
        TrainerHrExternalDTO secondExternal = trainerExternalDTOS.get(1);
        assertThat(secondExternal.getName()).isEqualTo(trainer2.getName());
        assertThat(secondExternal.getLastName()).isEqualTo(trainer2.getLastName());
        assertThat(secondExternal.getBiography()).isEqualTo(trainer2.getBiography());
    }
}



