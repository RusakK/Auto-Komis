package sda.project.autoKomis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sda.project.autoKomis.model.preparedModel.Manufacturer;

public interface ManufacturerRepository extends JpaRepository<Manufacturer, Integer> {

}
