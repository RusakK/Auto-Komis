package sda.project.autoKomis.service;

import sda.project.autoKomis.model.car.*;

import java.util.List;
import java.util.Optional;

public interface CarDataService {

    List<Car> loadCarsThatCanBeSold();

    Car addCar(Car newCarToBeSaved);

    Car getById(Integer carId);

    BodyType getBodyTypeById(Integer bodyTypeId);

    Fuel getFuelById(Integer fuelId);

    Model getModelByName(String modelName, Integer manufacturerId);

    Manufacturer getManufacturerByName(String manufacturerName);

    List<Manufacturer> getAllManufacturers();

    Manufacturer getManufacturerById(Integer manufacturerId);

    Optional<Car> findByBodyNumberaAndSoldTrue(String bodyNumber);


    List<Car> getAllCars();
}
