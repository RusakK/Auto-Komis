package sda.project.autoKomis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import sda.project.autoKomis.model.Car;
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

}
