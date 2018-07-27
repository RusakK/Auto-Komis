package sda.project.autoKomis.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import sda.project.autoKomis.model.car.Car;
import sda.project.autoKomis.model.car.Manufacturer;
import sda.project.autoKomis.model.dto.CarDto;
import sda.project.autoKomis.model.dto.SaleDto;
import sda.project.autoKomis.service.CarDataService;

import java.util.List;

@Controller
@RequestMapping("/auto-komis/online")
public class CarDataController {

    @Autowired
    private final CarDataService carDataService;

    @Autowired
    public CarDataController(CarDataService carDataService) {
        this.carDataService = carDataService;

    }

    @PreAuthorize("hasAnyRole('CLIENT','EMPLOYEE', 'MANAGER', 'ADMIN')")
    @GetMapping(value = "/cars")
    public String showCars(Model model, @RequestParam(defaultValue = "0") int page) {
        String text = "Lista samochodów dostępnych na sprzedaż";
        model.addAttribute("text", text);
        model.addAttribute("cars", carDataService.loadCarsThatCanBeSold(new PageRequest(page, 5)));
        return "pages/carsPage";

    }

    @PreAuthorize("hasAnyRole('EMPLOYEE', 'MANAGER', 'ADMIN')")
    @GetMapping(value = "/allcars")
    public String showAllCars(Model model, @RequestParam(defaultValue = "0") int page) {
        String text = "Panel Pracownika - lista wszystkich samochód w komisie";
        model.addAttribute("text", text);
        model.addAttribute("cars", carDataService.findAllForPages(new PageRequest(page, 5)));
        return "pages/carsPage";
    }

    @PreAuthorize("hasAnyRole('CLIENT','EMPLOYEE', 'MANAGER', 'ADMIN')")
    @RequestMapping("/cars/{id}")
    public String getCar(@PathVariable("id") Integer carId,
                         Model model) {
        Car car = carDataService.getById(carId);
        if (car != null) {
            model.addAttribute("car", car);
        }
        return "pages/carDetailsPage";
    }

    @PreAuthorize("hasAnyRole('CLIENT','EMPLOYEE', 'MANAGER', 'ADMIN')")
    @GetMapping("/newcar")
    public String prepareNewCarToSave(Model model) {
        model.addAttribute("newCar", new CarDto());
        List<Manufacturer> allManufacturers = carDataService.getAllManufacturers();
        model.addAttribute("allManufacturers", allManufacturers);
        return "pages/addCarPage";
    }


    @PreAuthorize("hasAnyRole('EMPLOYEE', 'MANAGER', 'ADMIN')")
    @GetMapping(value = "/cars/{id}/sell")
    public String prepareToSellCar(@PathVariable("id") Integer carId, Model model) {
        Car carToBeSold = carDataService.getById(carId);
        if (carToBeSold.isSold()) {
            System.out.println("Nie można ponownie sprzedać wozu");
            return "redirect:/auto-komis/online/cars";
        }
        SaleDto saleDto = new SaleDto();
        saleDto.setCar(carToBeSold);
        saleDto.setCarId(carId);
        model.addAttribute("saleDto", saleDto);
        return "pages/sellCarPage";
    }


}
