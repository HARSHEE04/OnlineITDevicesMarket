package com.example.onlineitdevicesmarket.Service;

import com.example.onlineitdevicesmarket.Model.Order;
import com.example.onlineitdevicesmarket.Model.User;
import com.example.onlineitdevicesmarket.Repository.OrderRepo;
import com.example.onlineitdevicesmarket.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepo orderRepository;
    private final UserRepo userRepository;

    @Autowired
    public OrderService(OrderRepo orderRepository, UserRepo userRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
    }

    public void addOrder(Order order) {
        System.out.println("Saving order: " + order);
        orderRepository.save(order);
    }

    public List<Order> getOrdersByUserEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            return orderRepository.findByUserEmail(email);
        } else {
            throw new RuntimeException("User not found with email: " + email);
        }
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public double getTotalSales() {
        return orderRepository.findAll().stream().mapToDouble(Order::getPrice).sum();
    }
}
