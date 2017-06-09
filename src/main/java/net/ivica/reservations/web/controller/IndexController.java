package net.ivica.reservations.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Locale;

@Controller
public class IndexController {

    @RequestMapping("/index.html")
    public String index() {
        return "index";
    }

    @RequestMapping("/")
    public String root(Locale locale) {
        return "redirect:/index.html";
    }

}
