package sda.project.autoKomis.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import sda.project.autoKomis.model.car.Car;
import sda.project.autoKomis.model.car.Manufacturer;
import sda.project.autoKomis.model.dto.CarDto;
import sda.project.autoKomis.model.dto.SaleDto;
import sda.project.autoKomis.service.CarDataService;

import java.util.List;

@Controller
@RequestMapping("/auto-komis")
public class CarDataController {

    private final CarDataService carDataService;

    @Autowired
    public CarDataController(CarDataService carDataService) {
        this.carDataService = carDataService;

    }

    @RequestMapping(value = "/cars", method = RequestMethod.GET)
    public String showCars(Model model) {
        List<Car> cars = carDataService.loadCarsThatCanBeSold();
        String text = "Panel Klienta - lista samochodów na sprzedaż";
        model.addAttribute("text", text);
        model.addAttribute("cars", cars);
        return "pages/carsPage";
    }

    @RequestMapping(value = "/allcars", method = RequestMethod.GET)
    public String showAllCars(Model model) {
        List<Car> cars = carDataService.getAllCars();
        String text = "Panel Pracownika - lista wszystkich samochód w komisie";
        model.addAttribute("text", text);
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


    @GetMapping(value = "/cars/{id}/sell")
    public String prepareToSellCar(@PathVariable("id") Integer carId, Model model) {
        Car carToBeSold = carDataService.getById(carId);
        if (carToBeSold.isSold()) {
            System.out.println("Nie można ponownie sprzedać wozu");
            return "redirect:/auto-komis/cars";
        }
        SaleDto saleDto = new SaleDto();
        saleDto.setCar(carToBeSold);
        saleDto.setCarId(carId);
        model.addAttribute("saleDto", saleDto);
        return "pages/sellCarPage";
    }


}
