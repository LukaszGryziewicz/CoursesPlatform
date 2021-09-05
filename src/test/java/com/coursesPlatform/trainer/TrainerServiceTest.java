package com.coursesPlatform.trainer;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class TrainerServiceTest {

    @Autowired
    private TrainerFacade trainerFacade;

    @Test
    void shouldAddTrainer() {
        //given
        TrainerDTO trainer = new TrainerDTO("ada", "ada");
        //when
        trainerFacade.add(trainer);
        //then
        assertThat(trainerFacade.findAllTrainers()).containsExactlyInAnyOrder(trainer);
    }

    @Test
    void shouldShowAllTrainers() {
        //given
        TrainerDTO trainers = new TrainerDTO("", "");
        trainerFacade.add(trainers);
        //when
        List<TrainerDTO> allTrainers = trainerFacade.findAllTrainers();
        //then
        assertThat(allTrainers).contains(trainers);
    }

    @Test
    public void shouldDeleteTrainer() {
        //given
        TrainerDTO trainer = new TrainerDTO("", "");
        trainerFacade.add(trainer);
        //when
        trainerFacade.deleteByNameAndLastName(trainer.getName(), trainer.getLastName());
        //then
        List<TrainerDTO> allTrainers = trainerFacade.findAllTrainers();
        assertThat(allTrainers).isEmpty();
    }

    @Test
    public void shouldCheckIfTrainerIsUpdated() throws TrainerNotFoundException {
        //given
        TrainerDTO trainer = new TrainerDTO("Adam", "Dominik", "siema", "123456789", "jestem przystojny");
        TrainerDTO newTrainer = new TrainerDTO("Adam", "Dominik", "sirma", "987654321", "jestem miły");
        trainerFacade.add(trainer);

        //when
        TrainerDTO update = trainerFacade.update(newTrainer);
        //then
        TrainerDTO byNameAndLastName = trainerFacade.findByNameAndLastName("Adam", "Dominik");
        assertThat(byNameAndLastName).isEqualTo(newTrainer);
    }
}



