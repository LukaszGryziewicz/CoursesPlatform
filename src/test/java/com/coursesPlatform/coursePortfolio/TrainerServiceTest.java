package com.coursesPlatform.coursePortfolio;

import org.assertj.core.api.Assertions;
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
    private TrainerService trainerService;
    @Autowired
    private TrainerRepository trainerRepository;




    @Test
    void shouldAddTrainer() {
        //given
        TrainerDTO trainer = new TrainerDTO("ada", "ada");
        //when
        trainerService.add(trainer);
        //then
        assertThat(trainerService.findAllTrainers()).containsExactlyInAnyOrder(trainer);
    }
    @Test
    void shouldShowAllTrainers() {
        //given
        TrainerDTO trainers = new TrainerDTO("", "");
        trainerService.add(trainers);
        //when
        List<TrainerDTO> allTrainers = trainerService.findAllTrainers();
        //then
        assertThat(allTrainers).contains(trainers);
    }
    @Test
    public void shouldDeleteTrainer() {
        //given
        TrainerDTO trainer = new TrainerDTO("","");
        trainerService.add(trainer);
        //when
        trainerService.deleteByNameAndLastName(trainer.getName(),trainer.getLastName());
        //then
        List<TrainerDTO> allTrainers = trainerService.findAllTrainers();
        assertThat(allTrainers).isEmpty();
    }
    @Test
    public void shouldCheckIfTrainerIsUpdated() {
        //given
        TrainerDTO trainer = new TrainerDTO("Adam","Dominik","siema","123456789","jestem przystojny");
        TrainerDTO newTrainer = new TrainerDTO("Adam","Dominik","sirma","987654321","jestem miły");
        trainerService.add(trainer);

        //when
        TrainerDTO update = trainerService.update(newTrainer);
        //then
        TrainerDTO byNameAndLastName = trainerService.findByNameAndLastName("Adam", "Dominik");
        assertThat(byNameAndLastName).isEqualTo(newTrainer);
    }
}



