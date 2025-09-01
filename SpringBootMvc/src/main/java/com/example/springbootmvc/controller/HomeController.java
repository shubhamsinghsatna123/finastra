package com.example.springbootmvc.controller;

import com.example.springbootmvc.entity.User;
import com.example.springbootmvc.repository.UserRepo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    @Autowired
    private UserRepo userRepo;


//    @GetMapping("/")
//    public String home(){
//        System.out.println("Hello");
//        return "home";
//    }



    // Show register page
    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    // Handle register
    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user, Model model, HttpServletRequest http) {
        userRepo.save(user);

        HttpSession  session = http.getSession();
        session.getId();
        model.addAttribute("success", "Registration successful! You can now login.");
        model.addAttribute("session",http.getSession().getId());
        return "register"; // stay on same page and show success
    }

    // Show login page
    @GetMapping("/")
    public String showLoginForm(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    // Handle login
    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        Model model) {
        User user = userRepo.findByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            model.addAttribute("name", username);
            return "welcome";
        } else {
            model.addAttribute("error", "Invalid username or password!");
            return "login"; // stays on login.jsp
        }
    }


}
