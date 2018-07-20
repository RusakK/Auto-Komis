package sda.project.autoKomis.repository.carRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import sda.project.autoKomis.model.car.Car;

import java.util.List;
import java.util.Optional;

public interface CarRepository extends CrudRepository<Car, Integer> {

    @Query("SELECT c FROM Car c WHERE c.sold = false")
    List<Car> findNotSoldCars();

    @Query("SELECT c FROM Car c WHERE c.sold = true AND c.bodyNumber LIKE :bodyNumber")
    Optional<Car> findByBodyNumberaAndSoldTrue(@Param("bodyNumber") String bodyNumber);


}
