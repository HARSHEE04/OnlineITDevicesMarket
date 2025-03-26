package com.example.onlineitdevicesmarket.Repository;

import com.example.onlineitdevicesmarket.Model.Order;
import com.example.onlineitdevicesmarket.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
    List<User> findByNameContainingIgnoreCase(String name);
}
