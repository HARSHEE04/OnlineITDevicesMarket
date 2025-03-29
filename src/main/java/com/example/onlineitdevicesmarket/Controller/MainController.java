package com.example.onlineitdevicesmarket.Controller;

import com.example.onlineitdevicesmarket.Model.Order;
import com.example.onlineitdevicesmarket.Model.User;
import com.example.onlineitdevicesmarket.Service.OrderService;
import com.example.onlineitdevicesmarket.Repository.UserRepo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
public class MainController {

    @GetMapping("/")
    public String mainPage() {
        return "index"; // Renders index.html
    }

    private final OrderService orderService;
    private final UserRepo userRepo; // Inject UserRepo to fetch users

    public MainController(OrderService orderService, UserRepo userRepo) {
        this.orderService = orderService;
        this.userRepo = userRepo;
    }

    @GetMapping("/order")
    public String addOrderForm(Model model) {
        model.addAttribute("order", new Order());
        return "OrderPage";
    }

    @PostMapping("/order")
    public String addOrder(@ModelAttribute("order") Order order, BindingResult bindingResult,
                           Model model, Principal principal) {
        if (bindingResult.hasErrors()) {
            System.out.println("Validation Errors: " + bindingResult.getAllErrors());
            model.addAttribute("order", order);
            return "OrderPage"; // If errors exist, stay on the form
        }

        // Ensure the user is logged in
        if (principal == null) {
            model.addAttribute("error", "You must be logged in to place an order.");
            return "Login"; // Redirect to login if user is not authenticated
        }

        String email = principal.getName(); // Get logged-in user's email
        Optional<User> userOpt = userRepo.findByEmail(email);

        if (userOpt.isEmpty()) {
            model.addAttribute("error", "User not found.");
            return "Login"; // Handle user not found scenario
        }

        User user = userOpt.get(); // Retrieve the user from the database
        order.setUser(user); // Set the user in the order

        orderService.addOrder(order); // Save the order with the user

        // Pass the username to the receipt page
        model.addAttribute("username", user.getName());

        return "RecieptPage"; // Redirect to receipt page after success
    }

    @GetMapping("/order-history")
    public String viewOrderHistory(@RequestParam String email, Model model) {
        List<Order> orders = orderService.getOrdersByUserEmail(email);

        if (orders.isEmpty()) {
            model.addAttribute("message", "No previous orders found.");
        } else {
            model.addAttribute("orders", orders);
        }

        return "OrderHistoryPage";
    }

    @PreAuthorize("hasRole('ADMIN')") // Restrict access to ADMINs only
    @GetMapping("/admin")
    public String adminDashboard(Model model) {
        List<Order> orders = orderService.getAllOrders();
        double totalSales = orderService.getTotalSales();

        model.addAttribute("orders", orders);
        model.addAttribute("totalSales", totalSales);

        return "AdminPage"; // Loads AdminPage.html
    }
}
