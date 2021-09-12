package com.coursesPlatform.coursePortfolio;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/order")
@CrossOrigin(origins = "http://localhost:4200/")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) { this.orderService = orderService; }

    @GetMapping("/all")
    ResponseEntity<List<OrderDTO>> getAllOrders(){
        List<OrderDTO> allOrders = orderService.showAllOrders();
        return new ResponseEntity<>(allOrders, HttpStatus.OK);
    }
    @PostMapping("/{offerId}/{orderStartDate}/{orderEndDate}")
    ResponseEntity<OrderDTO> addNewOrder(@PathVariable("offerId") String offerId ,
                                         @PathVariable("orderStartDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate orderStartDate,
                                         @PathVariable("orderEndDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate orderEndDate) {
        OrderDTO add = orderService.add(offerId, orderStartDate, orderEndDate);
        return new ResponseEntity<>(add, HttpStatus.CREATED);
    }

}
