package sda.project.autoKomis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import sda.project.autoKomis.model.Role;
import sda.project.autoKomis.model.User;
import sda.project.autoKomis.service.RoleService;
import sda.project.autoKomis.service.UserDataService;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;


@Controller
public class UserDataController {

    @Autowired
    private UserDataService userDataService;

    @Autowired
    private RoleService roleService;

    @GetMapping("/")
    public String showHome() {
        return "pages/home";
    }


    @RequestMapping("/auto-komis/login")
    public String login() {
        return "pages/loginPage";
    }

    @RequestMapping("/auto-komis/logout")
    public String logout() {
        return "pages/logoutPage";
    }


    @GetMapping("/auto-komis/register")
    public String register(Model model) {
        model.addAttribute("newUser", new User());
        return "pages/registrationPage";
    }

    @PostMapping("/auto-komis/register")
    public String addNewUser(@Valid @ModelAttribute("newUser") User newUser, BindingResult bindingResult) {

        if (checkUsernameEmailPasswordConfirm(newUser, bindingResult))
            return "pages/registrationPage";

        User user = new User();
        user.setUsername(newUser.getUsername());
        user.setPassword(newUser.getPassword());
        user.setPasswordConfirm(newUser.getPasswordConfirm());
        user.setEmail(newUser.getEmail());
        user.setActive(1);
        userDataService.addNewUser(user);
        return "redirect:/auto-komis/wait";
    }

    @GetMapping("/auto-komis/wait")
    public String waitForPrivilages() {
        return "pages/waitPage";
    }

    private boolean checkUsernameEmailPasswordConfirm(@ModelAttribute("newUser") @Valid User newUser, BindingResult bindingResult) {
        if (userDataService.findByUsername(newUser.getUsername()).isPresent()) {
            FieldError ssoError = new FieldError("newUser",
                    "username",
                    "Użytkownik o podanej nazwie już istnieje");
            bindingResult.addError(ssoError);
            return true;
        }

        if (userDataService.findByEmail(newUser.getEmail()).isPresent()) {
            FieldError ssoError = new FieldError("newUser",
                    "email",
                    "Podany email jest już w bazie");
            bindingResult.addError(ssoError);
            return true;
        }

        if (!newUser.getPassword().equals(newUser.getPasswordConfirm())) {
            FieldError ssoError = new FieldError("newUser",
                    "passwordConfirm",
                    "Hasła nie są takie same");
            bindingResult.addError(ssoError);
            return true;
        }
        return false;
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("/auto-komis/admin/users")
    public String getAllUsers(Model model) {
        List<User> allUsers = userDataService.getAllUsers();
        model.addAttribute("allUsers", allUsers);
        return "pages/allUsersPage";
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("/auto-komis/admin/users/{id}")
    public String getCar(@PathVariable("id") Integer userId,
                         Model model) {
        User user = userDataService.getById(userId);
        model.addAttribute("user", user);

        List<Role> allRoles = roleService.getAllRoles();
        model.addAttribute("roles", allRoles);
        return "pages/userDetailsPage";
    }

    @PostMapping("/auto-komis/admin/users")
    public String setPrivilages(@ModelAttribute("user") User user) {
        User userToChanges = userDataService.getById(user.getId());
        Set<Role> userRoles = user.getRoles();
        userToChanges.setRoles(userRoles);

        userDataService.updateRoles(userToChanges);

        return "redirect:/auto-komis/admin/users";
    }

    /* Strony w przygotowaniu*/

    @GetMapping("/auto-komis/online/depart-car")
    public String departCar(Model model) {
        model.addAttribute("text", "Strona w budowie");
        return "pages/inWorkPage";
    }

    @GetMapping("/auto-komis/online/your-cars")
    public String userCars(Model model) {
        model.addAttribute("text", "Strona w budowie");
        return "pages/inWorkPage";
    }


    @GetMapping("/auto-komis/online/clients/newclient")
    public String newClient(Model model) {
        model.addAttribute("text", "Strona w budowie");
        return "pages/inWorkPage";
    }


}
