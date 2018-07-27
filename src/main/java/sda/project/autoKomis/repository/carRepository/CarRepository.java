package sda.project.autoKomis.repository.carRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sda.project.autoKomis.model.car.Car;

import java.util.Optional;

public interface CarRepository extends JpaRepository<Car, Integer> {

    @Query("SELECT c FROM Car c WHERE c.sold = false")
    Page<Car> findNotSoldCars(Pageable var1);

    @Query("SELECT c FROM Car c WHERE c.sold = true AND c.bodyNumber LIKE :bodyNumber")
    Optional<Car> findByBodyNumberaAndSoldTrue(@Param("bodyNumber") String bodyNumber);


}
