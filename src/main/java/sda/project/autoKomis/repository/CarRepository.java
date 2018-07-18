package sda.project.autoKomis.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import sda.project.autoKomis.model.car.Car;

import java.util.List;

public interface CarRepository extends CrudRepository<Car, Integer> {

    @Query("SELECT c FROM Car c WHERE c.sold = false")
    List<Car> findNotSoldCars();
}
