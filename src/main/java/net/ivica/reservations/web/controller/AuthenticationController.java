package net.ivica.reservations.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AuthenticationController {

    @RequestMapping("/403.html")
    public String forbidden() {
        return "403";
    }

    @RequestMapping("/logout.html")
    public String logout() {
        return "index";
    }

    @RequestMapping("/login-error.html")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "index";
    }

}
