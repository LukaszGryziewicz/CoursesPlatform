package com.coursesPlatform.coursePortfolio;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class TrainerTest {

    @Autowired
    private TrainerService trainerService;

    @Test
    void shouldAddTrainer() {
        //when
        TrainerDTO trainer = trainerService.add("Adam", "Domnik", "abc@gmail.com");
        //then
        List<TrainerDTO> trainerList = trainerService.findAll();
        assertThat(trainerList).containsExactlyInAnyOrder(trainer);
    }

    @Test
    void shouldFindAllTrainers() {
        //given
        TrainerDTO trainer1 = trainerService.add("Adam", "Domnik", "abc@gmail.com");
        TrainerDTO trainer2 = trainerService.add("Dawid", "Jamka", "xyz@gmail.com");
        //when
        List<TrainerDTO> trainerList = trainerService.findAll();
        //then
        assertThat(trainerList).containsExactlyInAnyOrder(trainer1, trainer2);
    }
}
