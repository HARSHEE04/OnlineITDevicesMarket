package com.example.onlineitdevicesmarket.Service;
import com.example.onlineitdevicesmarket.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.onlineitdevicesmarket.Model.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepository;

    public void registerUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        userRepository.save(user);

    }
}
