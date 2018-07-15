package sda.project.autoKomis.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import sda.project.autoKomis.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUsername(String username);
}
