package sda.project.autoKomis.service;

import org.springframework.stereotype.Service;
import sda.project.autoKomis.model.Car;
import sda.project.autoKomis.repository.CarRepository;

import java.util.List;

@Service
public class DefaultCarDataService implements CarDataService {

    private final CarRepository carRepository;

    public DefaultCarDataService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }


    @Override
    public List<Car> loadCarsThatCanBeSold() {
        return carRepository.findNotSoldCar();
    }

    @Override
    public Car addCar(Car newCarToBeSaved) {
        return carRepository.save(newCarToBeSaved);
    }

    @Override
    public Car getById(Integer carId) {
        return carRepository.findOne(carId);
    }
}
