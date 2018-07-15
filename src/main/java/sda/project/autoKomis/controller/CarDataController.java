package sda.project.autoKomis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sda.project.autoKomis.model.Car;
import sda.project.autoKomis.model.CarDto;
import sda.project.autoKomis.model.preparedModel.Manufacturer;
import sda.project.autoKomis.service.CarDataService;

import java.util.List;

@Controller
@RequestMapping("/auto-komis")
public class CarDataController {

    private final CarDataService carDataService;

    public CarDataController(CarDataService carDataService) {
        this.carDataService = carDataService;
    }


    @RequestMapping(value = "/cars", method = RequestMethod.GET)
    public String showCars(Model model) {
        List<Car> cars = carDataService.loadCarsThatCanBeSold();
        model.addAttribute("cars", cars);
        return "cars";
    }

    @RequestMapping("/cars/{id}")
    public String getCar(@PathVariable("id") Integer carId,
                         Model model) {
        Car car = carDataService.getById(carId);
        if (car != null) {
            model.addAttribute("car", car);
        }
        return "carDetails";
    }

    @GetMapping("/newCar")
    public String prepareNewCarToSave(Model model) {
        System.out.println("robi obiekty");

        model.addAttribute("newCar", new CarDto());
        model.addAttribute("manufacturer", new Manufacturer());
        return "addCar";
    }

    @PostMapping("/cars")
    public String saveVehicle(@ModelAttribute("newCar") CarDto carToBeSave) {
        System.out.println("dodaje");

        Car car = new Car();


        car.setManufacturer(carToBeSave.getManufacturer());
        car.setModel(carToBeSave.getModel());

        car.setBodyType(carDataService.getBodyTypeById(carToBeSave.getBodyType()));
        car.setFuel(carDataService.getFuelById(carToBeSave.getFuel()));

        car.setBodyNumber(carToBeSave.getBodyNumber());
        car.setProductionYear(carToBeSave.getProductionYear());
        car.setInsuranceNumber(carToBeSave.getInsuranceNumber());
        car.setDocumentNumber(carToBeSave.getDocumentNumber());
        car.setMileage(carToBeSave.getMileage());
        car.setEngine(carToBeSave.getEngine());
        car.setPower(carToBeSave.getPower());
//        car.setTransmission(carToBeSave.getTransmission());
        car.setDescription(carToBeSave.getDescription());
        car.setPrice(carToBeSave.getPrice());

        carDataService.addCar(car);
        return "redirect:/auto-komis/cars";
    }

}
