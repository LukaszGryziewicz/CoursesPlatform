package com.coursesPlatform.coursePortfolio;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    CustomerDTO add(CustomerDTO customerDTO) {
        Optional<Customer> customerByMail = customerRepository
                .findCustomerByMail(customerDTO.getMail());
        if(!EmailValidator.getInstance().isValid(customerDTO.getMail())){
            throw new MailInvalidException();
        }
        if(customerByMail.isPresent()){
            throw new MailIsAlreadyInUseException();
        }
        Customer customer = customerRepository.save(convertDTOToCustomer(customerDTO));
        return convertCustomerToDTO(customer);
    }

    List<CustomerDTO> findAllCustomers() {
        return customerRepository.findAll()
                .stream()
                .map(this::convertCustomerToDTO)
                .collect(Collectors.toList());
    }

    CustomerDTO update(String mail, CustomerDTO updatedCustomer) {
        Optional<Customer> optionalCustomer = customerRepository
                .findCustomerByMail((mail));
        Customer existingCustomer = optionalCustomer.orElseThrow(CustomerNotFoundException::new);
        existingCustomer.update(convertDTOToCustomer(updatedCustomer));
        customerRepository.save(existingCustomer);
        return convertCustomerToDTO(existingCustomer);
    }

    void deleteCustomerByEmail(String mail) {
        customerRepository.deleteCustomerByMail(mail);
    }

    private CustomerDTO convertCustomerToDTO(Customer customer) {
        return new CustomerDTO(
                customer.getName(),
                customer.getMail(),
                customer.getPhoneNumber()
        );
    }

    private Customer convertDTOToCustomer(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        customer.setName(customerDTO.getName());
        customer.setMail(customerDTO.getMail());
        customer.setPhoneNumber(customerDTO.getPhoneNumber());
        return customer;

    }
}
