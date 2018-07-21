package sda.project.autoKomis.service;

import org.springframework.stereotype.Service;
import sda.project.autoKomis.model.car.*;
import sda.project.autoKomis.repository.carRepository.*;

import java.util.List;
import java.util.Optional;

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
        return carRepository.findNotSoldCars();
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

    @Override
    public Model getModelByName(String modelName, java.lang.Integer manufacturerId) {
        Model modelByName = modelRepository.getModelByName(modelName);
        if (modelByName != null) {
            modelByName.setManufacturer(manufacturerRepository.getById(manufacturerId));
            return modelByName;
        }
        Model newModel = new Model();
        newModel.setName(modelName);
        newModel.setManufacturer(manufacturerRepository.getById(manufacturerId));

        return newModel;
    }

    @Override
    public Manufacturer getManufacturerByName(String manufacturerName) {
        Manufacturer manufacturerByName = manufacturerRepository.getManufacturerByName(manufacturerName);
        if (manufacturerByName != null) {
            return manufacturerByName;
        }
        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setName(manufacturerName);
        return manufacturer;
    }

    @Override
    public List<Manufacturer> getAllManufacturers() {
        return manufacturerRepository.findAll();
    }

    @Override
    public Manufacturer getManufacturerById(Integer manufacturerId) {
        return manufacturerRepository.getById(manufacturerId);
    }

    @Override
    public Optional<Car> findByBodyNumberaAndSoldTrue(String bodyNumber) {
        return carRepository.findByBodyNumberaAndSoldTrue(bodyNumber);
    }

    @Override
    public List<Car> getAllCars() {
        return (List<Car>) carRepository.findAll();
    }


}
