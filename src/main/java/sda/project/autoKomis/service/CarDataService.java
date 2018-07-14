package sda.project.autoKomis.service;

import sda.project.autoKomis.model.Car;

import java.util.List;

public interface CarDataService {

    List<Car> loadCarsThatCanBeSold();

    Car addCar(Car newCarToBeSaved);

    Car getById(Integer carId);
}
