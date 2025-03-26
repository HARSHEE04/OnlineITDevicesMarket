package com.example.onlineitdevicesmarket.Repository;

import com.example.onlineitdevicesmarket.Model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepo extends JpaRepository<Order, Integer> {
    List<Order> findbyuId(int uId);
}


