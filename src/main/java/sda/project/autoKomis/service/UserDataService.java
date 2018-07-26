package sda.project.autoKomis.service;

import sda.project.autoKomis.model.User;

import java.util.List;
import java.util.Optional;

public interface UserDataService {

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    User addNewUser(User user);


    List<User> getAllUsers();


    User getById(Integer userId);


    void updateRoles(User user);
}
