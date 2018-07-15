package sda.project.autoKomis.service;

import org.springframework.stereotype.Service;
import sda.project.autoKomis.model.Car;
import sda.project.autoKomis.model.preparedModel.BodyType;
import sda.project.autoKomis.model.preparedModel.Fuel;
import sda.project.autoKomis.repository.*;

import java.util.List;

@Service
public class DefaultCarDataService implements CarDataService {

    private final CarRepository carRepository;
    private final BodyTypeRepository bodyTypeRepository;
    private final FuelRepository fuelRepository;
    private final ManufacturerRepository manufacturerRepository;
    private final ModelRepository modelRepository;


    public DefaultCarDataService(CarRepository carRepository, BodyTypeRepository bodyTypeRepository, FuelRepository fuelRepository, ManufacturerRepository manufacturerRepository, ModelRepository modelRepository) {
        this.carRepository = carRepository;
        this.bodyTypeRepository = bodyTypeRepository;
        this.fuelRepository = fuelRepository;
        this.manufacturerRepository = manufacturerRepository;
        this.modelRepository = modelRepository;
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

    @Override
    public BodyType getBodyTypeById(Integer bodyTypeId) {
        return bodyTypeRepository.findOne(bodyTypeId);
    }

    @Override
    public Fuel getFuelById(Integer fuelId) {
        return fuelRepository.findOne(fuelId);
    }


}
