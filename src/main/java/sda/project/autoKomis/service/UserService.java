package sda.project.autoKomis.service;

import sda.project.autoKomis.model.User;

public interface UserService {
    void save(User user);

    User findByUsername(String username);
}
