package sda.project.autoKomis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sda.project.autoKomis.model.car.Manufacturer;

public interface ManufacturerRepository extends JpaRepository<Manufacturer, Integer> {

    Manufacturer getManufacturerByName(String manufacturerName);

    Manufacturer getById(Integer manufacturerId);

}
