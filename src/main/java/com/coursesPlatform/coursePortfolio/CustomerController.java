package com.coursesPlatform.coursePortfolio;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
@CrossOrigin(origins = "http://localhost:4200/")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }


    @GetMapping("/all")
    ResponseEntity<List<CustomerDTO>> getAllCustomers() {
        List<CustomerDTO> allCustomers = customerService.findAllCustomers();
        return new ResponseEntity<>(allCustomers, HttpStatus.OK);
    }

    @PostMapping("/add")
    ResponseEntity<CustomerDTO> addNewCustomer(@RequestBody CustomerDTO customerDTO) {
        CustomerDTO add = customerService.add(customerDTO);
        return new ResponseEntity<>(add, HttpStatus.CREATED);
    }

    @PutMapping("/update/{mail}")
    ResponseEntity<CustomerDTO> updateTrainer(@RequestBody CustomerDTO customerDTO,@PathVariable("mail")String mail) {
        CustomerDTO update = customerService.update(mail , customerDTO);
        return new ResponseEntity<>(update, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{mail}")
    ResponseEntity<?> deleteCustomer(@PathVariable("mail") String mail) {
        customerService.deleteCustomerByEmail(mail);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
