package sda.project.autoKomis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sda.project.autoKomis.model.car.Model;

public interface ModelRepository extends JpaRepository<Model, Integer> {

    Model getModelByName(String modelName);

}
