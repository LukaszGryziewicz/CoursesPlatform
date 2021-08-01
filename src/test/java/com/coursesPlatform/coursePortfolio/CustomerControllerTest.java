package com.coursesPlatform.coursePortfolio;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.hamcrest.Matchers.*;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@Transactional
@AutoConfigureMockMvc
public class CustomerControllerTest {
    ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private CustomerService customerService;

    @Test
    void shouldAddCustomer() throws Exception {
        //given
        CustomerDTO customerDTO = new CustomerDTO("Adam", "dominator.aligator@gmail.com", "123123123");
        String content = objectMapper.writeValueAsString(customerDTO);
        //expect
        mockMvc.perform(post("/customer/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content))
                .andExpect(status().is(201))
                .andDo(print())
                .andExpect(jsonPath("$.name").value(customerDTO.getName()))
                .andExpect(jsonPath("$.mail").value(customerDTO.getMail()))
                .andExpect(jsonPath("$.phoneNumber").value(customerDTO.getPhoneNumber()));
    }

    @Test
    public void shouldGetAllCustomers() throws Exception {
        //given
        CustomerDTO customerDTO = new CustomerDTO("Adam", "dominator.aligator@gmail.com", "123123123");
        customerService.add(customerDTO);
        //expect
        this.mockMvc.perform(get("/customer/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value(customerDTO.getName()))
                .andExpect(jsonPath("$[0].mail").value(customerDTO.getMail()))
                .andExpect(jsonPath("$[0].phoneNumber").value(customerDTO.getPhoneNumber()));
    }

    @Test
    public void shouldGetUpdatedCustomer() throws Exception {
        //given
        CustomerDTO customerDTO = new CustomerDTO("Adam", "dominator.aligator@gmail.com", "123123123");
        customerService.add(customerDTO);
        CustomerDTO customerDTO2 = new CustomerDTO("Patryk", "patrykowiec.sosnowiec@gmail.com", "987987987");
        String content = objectMapper.writeValueAsString(customerDTO2);
        //expect
        mockMvc.perform(put("/customer/update/dominator.aligator@gmail.com")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(customerDTO2.getName()))
                .andExpect(jsonPath("$.mail").value(customerDTO2.getMail()))
                .andExpect(jsonPath("$.phoneNumber").value(customerDTO2.getPhoneNumber()));

        this.mockMvc.perform(get("/customer/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value(customerDTO2.getName()))
                .andExpect(jsonPath("$[0].mail").value(customerDTO2.getMail()))
                .andExpect(jsonPath("$[0].phoneNumber").value(customerDTO2.getPhoneNumber()));
    }

    @Test
    public void shouldGetDeleteCustomer() throws Exception {
        //given
        CustomerDTO customerDTO = new CustomerDTO("Adam", "dominator.aligator@gmail.com", "123123123");
        customerService.add(customerDTO);
        List<CustomerDTO> emptyList = new ArrayList<>();

        //expect
        this.mockMvc.perform(delete("/customer/delete/dominator.aligator@gmail.com"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").doesNotExist());

        this.mockMvc.perform(get("/customer/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }
}
