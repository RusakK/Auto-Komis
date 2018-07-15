package sda.project.autoKomis.service;

import sda.project.autoKomis.model.Car;
import sda.project.autoKomis.model.preparedModel.BodyType;
import sda.project.autoKomis.model.preparedModel.Fuel;

import java.util.List;

public interface CarDataService {

    List<Car> loadCarsThatCanBeSold();

    Car addCar(Car newCarToBeSaved);

    Car getById(Integer carId);

    BodyType getBodyTypeById(Integer bodyTypeId);

    Fuel getFuelById(Integer fuelId);


}
