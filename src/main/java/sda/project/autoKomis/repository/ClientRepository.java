package sda.project.autoKomis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sda.project.autoKomis.model.Client;
import sda.project.autoKomis.model.Person;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Integer> {

    Optional<Person> findByPesel(Integer pesel);
}
