package com.coursesPlatform.trainer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Transactional
@AutoConfigureMockMvc
class TrainerControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private TrainerService trainerService;
    ObjectMapper objectMapper = new ObjectMapper();


    @Test
    void shouldAddTrainer() throws Exception {
        //given
        TrainerDTO trainer = new TrainerDTO("Adam", "Dominik", "", "", "");
        TrainerDTO trainer2 = new TrainerDTO("Dominik", "Adam", "", "", "");
        String content = objectMapper.writeValueAsString(trainer);
        //expect
        mockMvc.perform(
                post("/trainer/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andDo(print())
                .andExpect(status().is(201))
                .andExpect(jsonPath("$.name").value(trainer.getName()))
                .andExpect(jsonPath("$.lastName").value(trainer.getLastName()))
                .andExpect(jsonPath("$.mail").value(trainer.getMail()))
                .andExpect(jsonPath("$.phoneNumber").value(trainer.getPhoneNumber()))
                .andExpect(jsonPath("$.biography").value(trainer.getBiography()));
        String content1 = objectMapper.writeValueAsString(trainer2);
        mockMvc.perform(
                post("/trainer/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content1))
                .andDo(print())
                .andExpect(status().is(201))
                .andExpect(jsonPath("$.name").value(trainer2.getName()))
                .andExpect(jsonPath("$.lastName").value(trainer2.getLastName()))
                .andExpect(jsonPath("$.mail").value(trainer2.getMail()))
                .andExpect(jsonPath("$.phoneNumber").value(trainer2.getPhoneNumber()))
                .andExpect(jsonPath("$.biography").value(trainer2.getBiography()));
    }

    @Test
    void shouldReturnAllTrainers() throws Exception {
        //given
        TrainerDTO trainer = new TrainerDTO("Adam", "Dominik", "", "", "");
        trainerService.add(trainer);
        //expect
        this.mockMvc.perform(get("/trainer/all/internal")).andDo(print())
                //then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value(trainer.getName()))
                .andExpect(jsonPath("$[0].lastName").value(trainer.getLastName()))
                .andExpect(jsonPath("$[0].mail").value(trainer.getMail()))
                .andExpect(jsonPath("$[0].phoneNumber").value(trainer.getPhoneNumber()))
                .andExpect(jsonPath("$[0].biography").value(trainer.getBiography()));

    }

    @Test
    void shouldDeleteTrainer() throws Exception {
        //given
        TrainerDTO trainer = new TrainerDTO("Adam", "Dominik", "", "", "");
        trainerService.add(trainer);
        //expect
        this.mockMvc.perform(delete("/trainer/delete/Adam/Dominik"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").doesNotExist());

        this.mockMvc.perform(get("/trainer/all/internal"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    void shouldUpdateTrainer() throws Exception {
        //given
        TrainerDTO trainer = new TrainerDTO("Adam", "Dominik", "adamo@gmail.com", "123456789", "1");
        trainerService.add(trainer);
        TrainerDTO trainer2 = new TrainerDTO("Patryk", "Żak", "patryko@gmail.com", "987654321", "2");
        String content = objectMapper.writeValueAsString(trainer2);
        //expect
        mockMvc.perform(put("/trainer/Adam/Dominik")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(trainer2.getName()))
                .andExpect(jsonPath("$.lastName").value(trainer2.getLastName()))
                .andExpect(jsonPath("$.mail").value(trainer2.getMail()))
                .andExpect(jsonPath("$.phoneNumber").value(trainer2.getPhoneNumber()))
                .andExpect(jsonPath("$.biography").value(trainer2.getBiography()));

        System.out.println("123");
        this.mockMvc.perform(get("/trainer/all/internal"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value(trainer2.getName()))
                .andExpect(jsonPath("$[0].lastName").value(trainer2.getLastName()))
                .andExpect(jsonPath("$[0].mail").value(trainer2.getMail()))
                .andExpect(jsonPath("$[0].phoneNumber").value(trainer2.getPhoneNumber()))
                .andExpect(jsonPath("$[0].biography").value(trainer2.getBiography()));
    }
}