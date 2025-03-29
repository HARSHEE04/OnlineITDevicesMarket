package com.example.onlineitdevicesmarket.Repository;

import com.example.onlineitdevicesmarket.Model.Order;
import com.example.onlineitdevicesmarket.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email); // Ensure this method exists
}
