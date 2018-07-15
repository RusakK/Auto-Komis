package sda.project.autoKomis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import sda.project.autoKomis.model.User;


@Controller
public class UserDataController {

    @RequestMapping("/login")
    public String login(Model model) {
        model.addAttribute("user", new User());
        return "logowanie";
    }


}
