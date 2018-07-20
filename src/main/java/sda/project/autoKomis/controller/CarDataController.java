package sda.project.autoKomis.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import sda.project.autoKomis.model.car.Car;
import sda.project.autoKomis.model.car.Manufacturer;
import sda.project.autoKomis.model.car.Transmission;
import sda.project.autoKomis.model.dto.CarDto;
import sda.project.autoKomis.model.dto.PurchaseDto;
import sda.project.autoKomis.service.CarDataService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

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
        return "pages/carsPage";
    }

    @RequestMapping("/cars/{id}")
    public String getCar(@PathVariable("id") Integer carId,
                         Model model) {
        Car car = carDataService.getById(carId);
        if (car != null) {
            model.addAttribute("car", car);
        }
        return "pages/carDetailsPage";
    }

    @GetMapping("/newcar")
    public String prepareNewCarToSave(Model model) {
        model.addAttribute("newCar", new CarDto());
        List<Manufacturer> allManufacturers = carDataService.getAllManufacturers();
        model.addAttribute("allManufacturers", allManufacturers);
        return "pages/addCarPage";
    }

    @PostMapping("/cars")
    public String saveVehicle(@Valid @ModelAttribute("newCar") CarDto carToBeSave,
                              BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "pages/addCarPage";
        }
        Optional<Car> byBodyNumberaAndSoldTrue = carDataService.findByBodyNumberaAndSoldTrue(carToBeSave.getBodyNumber());
        if (byBodyNumberaAndSoldTrue.isPresent()) {
            FieldError ssoError = new FieldError("newCar", "bodyNumber", "Samochód już był sprzedany prze komis");
            bindingResult.addError(ssoError);
            return "pages/addCarPage";

        }
        Car car = getDataFromCarToBeSaveAndCreateCar(carToBeSave);
        carDataService.addCar(car);
        return "redirect:/auto-komis/cars";
    }

    @GetMapping(value = "/cars/{id}/sell")
    public String prepareToSellCar(@PathVariable("id") Integer carId, Model model) {
        Car carToBeSold = carDataService.getById(carId);
        if (carToBeSold.isSold()) {
            System.out.println("Nie można ponownie sprzedać wozu");
            return "redirect:/auto-komis/cars";
        }
        PurchaseDto purchaseDto = new PurchaseDto();
        purchaseDto.setCar(carToBeSold);
        purchaseDto.setCarId(carId);
        model.addAttribute("purchaseDto", purchaseDto);
        return "pages/sellCarPage";
    }

    private Car getDataFromCarToBeSaveAndCreateCar(@Valid @ModelAttribute("newCar") CarDto carToBeSave) {
        Car car = new Car();
        car.setManufacturer(carDataService.getManufacturerById(carToBeSave.getManufacturer()));
        car.setModel(carDataService.getModelByName(carToBeSave.getModel(), carToBeSave.getManufacturer()));
        car.setTransmission(Transmission.getTransmission(carToBeSave.getTransmission()));
        car.setBodyType(carDataService.getBodyTypeById(carToBeSave.getBodyType()));
        car.setFuel(carDataService.getFuelById(carToBeSave.getFuel()));
        car.setBodyNumber(carToBeSave.getBodyNumber());
        car.setProductionYear(carToBeSave.getProductionYear());
        car.setInsuranceNumber(carToBeSave.getInsuranceNumber());
        car.setDocumentNumber(carToBeSave.getDocumentNumber());
        car.setMileage(carToBeSave.getMileage());
        car.setEngine(carToBeSave.getEngine());
        car.setPower(carToBeSave.getPower());
        car.setDescription(carToBeSave.getDescription());
        car.setPrice(carToBeSave.getPrice());
        return car;
    }

}
