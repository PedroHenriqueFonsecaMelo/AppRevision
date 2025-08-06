package app.revision.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GreetingController {

    @GetMapping("/greetings")
    public String greeting(Model model) {
        model.addAttribute("message", "Welcome to our website!");
        return "greeting"; // Thymeleaf template name (greeting.html)
    }
}
