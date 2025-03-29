package com.example.onlineitdevicesmarket.Controller;

import com.example.onlineitdevicesmarket.Model.User;
import com.example.onlineitdevicesmarket.Repository.UserRepo;
import com.example.onlineitdevicesmarket.Service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class RegistrationController {

    private final UserService userService;
    private final UserRepo userRepo;

    public RegistrationController(UserService userService, UserRepo userRepo) {
        this.userService = userService;
        this.userRepo = userRepo;
    }

    // ✅ Show Registration Page
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        System.out.println("Showing registration form");
        model.addAttribute("user", new User());
        return "RegistrationPage"; // Thymeleaf template for registration
    }

    // ✅ Handle User Registration
    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user, Model model) {
        System.out.println("Received registration request for: " + user.getEmail());
        userService.registerUser(user);
        model.addAttribute("successMessage", "Registration successful! You can now log in.");
        return "Login"; // Redirect to login page after successful registration
    }

    // ✅ Show Login Page
    @GetMapping("/login")
    public String showLoginForm() {
        return "Login"; // Thymeleaf template for login page
    }




}

