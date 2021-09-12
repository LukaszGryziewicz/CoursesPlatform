package com.coursesPlatform.coursePortfolio;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class TrainerProjectionTest {

    @Autowired
    private OrderFacade orderFacade;

    @Test
    void shouldAddTrainer() {
        //when
        TrainerProjectionDTO trainer = orderFacade.trainerCreated("Adam", "Domnik", "abc@gmail.com");
        //then
        List<TrainerProjectionDTO> trainerList = orderFacade.findAll();
        assertThat(trainerList).containsExactlyInAnyOrder(trainer);
    }

    @Test
    void shouldFindAllTrainers() {
        //given
        TrainerProjectionDTO trainer1 = orderFacade.trainerCreated("Adam", "Domnik", "abc@gmail.com");
        TrainerProjectionDTO trainer2 = orderFacade.trainerCreated("Dawid", "Jamka", "xyz@gmail.com");
        //when
        List<TrainerProjectionDTO> trainerList = orderFacade.findAll();
        //then
        assertThat(trainerList).containsExactlyInAnyOrder(trainer1, trainer2);
    }

    @Test
    void shouldAssignVacationToTrainer() {
        //given
        TrainerProjectionDTO trainer = orderFacade.trainerCreated("Adam", "Domnik", "abc@gmail.com");
        LocalDate vacationStart = LocalDate.of(2021, 9, 12);
        LocalDate vacationEnd = LocalDate.of(2021, 9, 13);
        List<LocalDate> vacation = List.of(vacationStart, vacationEnd);
        //when
        orderFacade.vacationRegistered(trainer.getName(), trainer.getLastName(), vacation);
        //then
        assertThat(trainer.getUnavailableDays().toArray()).isEqualTo(vacation.toArray());
    }
}
