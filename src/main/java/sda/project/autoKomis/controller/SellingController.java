package sda.project.autoKomis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import sda.project.autoKomis.model.car.Car;
import sda.project.autoKomis.model.dto.PurchaseDto;
import sda.project.autoKomis.repository.CarRepository;


@Controller
@RequestMapping("/auto-komis/cars")
public class SellingController {

    private final CarRepository carRepository;

    public SellingController(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @RequestMapping(value = "/{id}/sell", method = RequestMethod.GET)
    public String sellCar(@PathVariable("id") Integer carId, Model model) {
        Car carToBeSold = carRepository.findOne(carId);
        if (carToBeSold.isSold()) {
            return "redirect/auto-komis/cars";
        }
        PurchaseDto purchaseDto = new PurchaseDto();
        model.addAttribute("car", carToBeSold);
        model.addAttribute(purchaseDto);
        return "pages/sellCarPage";
    }


}
