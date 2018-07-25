package sda.project.autoKomis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class UserDataController {


    @GetMapping("/")
    public String showHome() {
        return "pages/home";
    }


    @RequestMapping("/login")
    public String login() {
        return "pages/loginPage";
    }

    @RequestMapping("/auto-komis/logout")
    public String logout() {
        return "pages/logoutPage";
    }


}
