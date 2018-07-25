package sda.project.autoKomis.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import sda.project.autoKomis.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUsername(String username);
}
