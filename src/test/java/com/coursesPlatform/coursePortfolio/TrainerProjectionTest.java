package com.coursesPlatform.coursePortfolio;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class TrainerProjectionTest {

    @Autowired
    private TrainerProjectionService trainerService;

    @Test
    void shouldAddTrainer() {
        //when
        TrainerProjectionDTO trainer = trainerService.add("Adam", "Domnik", "abc@gmail.com");
        //then
        List<TrainerProjectionDTO> trainerList = trainerService.findAll();
        assertThat(trainerList).containsExactlyInAnyOrder(trainer);
    }

    @Test
    void shouldFindAllTrainers() {
        //given
        TrainerProjectionDTO trainer1 = trainerService.add("Adam", "Domnik", "abc@gmail.com");
        TrainerProjectionDTO trainer2 = trainerService.add("Dawid", "Jamka", "xyz@gmail.com");
        //when
        List<TrainerProjectionDTO> trainerList = trainerService.findAll();
        //then
        assertThat(trainerList).containsExactlyInAnyOrder(trainer1, trainer2);
    }
}
