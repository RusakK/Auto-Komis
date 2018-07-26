package sda.project.autoKomis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sda.project.autoKomis.model.User;
import sda.project.autoKomis.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DefaultUserDataService implements UserDataService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User addNewUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    @Override
    public User getById(Integer userId) {
        return userRepository.findOne(userId);
    }

    @Override
    public void updateRoles(User user) {
        userRepository.save(user);
    }
}
