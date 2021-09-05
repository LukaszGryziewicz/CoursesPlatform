package com.coursesPlatform.coursePortfolio;

import org.springframework.stereotype.Service;

@Service
class OrderService {

    private final OrderRepository orderRepository;

    OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

}
