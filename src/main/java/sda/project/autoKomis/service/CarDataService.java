package sda.project.autoKomis.service;

import sda.project.autoKomis.model.car.*;

import java.util.List;
import java.util.Optional;

public interface CarDataService {

    // dotyczy samochoów
    List<Car> loadCarsThatCanBeSold();

    List<Car> getAllCars();

    Car addCar(Car newCarToBeSaved);

    Car getById(Integer carId);

    Optional<Car> findByBodyNumberaAndSoldTrue(String bodyNumber);

    //dotyczy marki samochodu
    List<Manufacturer> getAllManufacturers();

    Manufacturer getManufacturerByName(String manufacturerName);

    Manufacturer getManufacturerById(Integer manufacturerId);

    // dotyczy modelu samochodu
    Model getModelByName(String modelName, Integer manufacturerId);

    // dotyczy części samochodu
    BodyType getBodyTypeById(Integer bodyTypeId);

    Fuel getFuelById(Integer fuelId);


}
