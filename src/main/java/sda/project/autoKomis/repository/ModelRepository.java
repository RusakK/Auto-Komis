package sda.project.autoKomis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sda.project.autoKomis.model.preparedModel.Model;

public interface ModelRepository extends JpaRepository<Model, Integer> {

}
