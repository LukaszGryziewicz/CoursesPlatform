package com.coursesPlatform.coursePortfolio;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

@SpringBootTest
@Transactional
public class CustomerTest {
    @Autowired
    private CustomerService customerService;

    @Test
    void shouldAddCustomer() {
        //given
        CustomerDTO customer = new CustomerDTO("av", "Saku", "123456789");
        //when
        customerService.add(customer);
        //then
        assertThat(customerService.findAllCustomers()).containsExactlyInAnyOrder(customer);
    }

    @Test
    void shouldShowAllCustomers() {
        //given
        CustomerDTO customers = new CustomerDTO("", "", "");
        customerService.add(customers);
        //when
        List<CustomerDTO> allCustomers = customerService.findAllCustomers();
        //then
        assertThat(allCustomers).contains(customers);
    }

    @Test
    public void shouldDeleteCustomer() {
        //given
        CustomerDTO customer = new CustomerDTO("", "", "");
        customerService.add(customer);
        //when
        customerService.deleteCustomerByEmail(customer.getMail());
        //then
        List<CustomerDTO> allCustomers = customerService.findAllCustomers();
        assertThat(allCustomers).isEmpty();
    }

    @Test
    public void shouldCheckIfTrainerIsUpdated() {
        //given
        CustomerDTO customer = new CustomerDTO("Adam", "Dominik", "siema");
        CustomerDTO newCustomer = new CustomerDTO("Adam", "Dominik", "sirma");
        customerService.add(customer);

        //when
        customerService.update(customer.getMail(), newCustomer);
        //then
        List<CustomerDTO> allCustomers = customerService.findAllCustomers();
        assertThat(allCustomers).containsExactly(newCustomer);
    }
    @Test
    public void shouldThrowExceptionWhenMailIsInUse() {
        //given
        CustomerDTO customer = new CustomerDTO("Adam", "dominik.adam4538@gmail.com", "123456789");
        customerService.add(customer);
        CustomerDTO newCustomer = new CustomerDTO("Lukasz", "dominik.adam4538@gmail.com", "987654321");
        //when
       Throwable thrown = catchThrowable(()-> customerService.add(newCustomer));
        //then
        assertThat(thrown).isInstanceOf(MailIsAlreadyInUseException.class);
    }
}
