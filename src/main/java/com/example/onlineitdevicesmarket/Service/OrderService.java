package com.example.onlineitdevicesmarket.Service;

import com.example.onlineitdevicesmarket.Model.Order;
import com.example.onlineitdevicesmarket.Repository.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    OrderRepo orderRepository;

    //have method to add order
    public void addOrder(Order order){
        System.out.println("Saving order: " + order); // Debugging
        orderRepository.save(order);
    }


}
