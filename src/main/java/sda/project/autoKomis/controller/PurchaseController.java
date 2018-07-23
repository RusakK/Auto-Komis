package sda.project.autoKomis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import sda.project.autoKomis.model.Purchase;
import sda.project.autoKomis.model.Sale;
import sda.project.autoKomis.model.Transaction;
import sda.project.autoKomis.model.car.Car;
import sda.project.autoKomis.model.car.Transmission;
import sda.project.autoKomis.model.dto.CarDto;
import sda.project.autoKomis.service.CarDataService;
import sda.project.autoKomis.service.PurchasingService;
import sda.project.autoKomis.service.SellingService;

import javax.validation.Valid;
import java.util.*;

@Controller
@RequestMapping("/auto-komis/purchases")
public class PurchaseController {

    private final PurchasingService purchasingService;
    private final CarDataService carDataService;
    private final SellingService sellingService;


    @Autowired
    public PurchaseController(PurchasingService purchasingService, CarDataService carDataService, SellingService sellingService) {
        this.purchasingService = purchasingService;

        this.carDataService = carDataService;
        this.sellingService = sellingService;
    }


    @RequestMapping(method = RequestMethod.GET)
    public String showPurchases(Model model) {
        List<Purchase> allPurchases = purchasingService.getAllPurchases();
        String text = "Panel Managera - kupno";
        model.addAttribute("text", text);
        Integer sumabrutto = 0;
        double sumanetto = 0;
        double zysknetto = 0;
        double podatek = 0;

        for (Purchase purchase : allPurchases) {
            sumabrutto += purchase.getPrice();
            sumanetto += purchase.getPrice() - (purchase.getPrice() * 0.19);
            zysknetto += (purchase.getPrice() - (purchase.getPrice() * 0.19)) - purchase.getCar().getPrice();
            podatek += purchase.getPrice() * 0.19;
        }
        model.addAttribute("sumabrutto", sumabrutto);
        model.addAttribute("sumanetto", sumanetto);
        model.addAttribute("zysknetto", zysknetto);
        model.addAttribute("sumapodatek", podatek);
        model.addAttribute("allPurchases", allPurchases);
        return "pages/purchasesPage";
    }

    @PostMapping("/newcar")
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
        Purchase purchase = new Purchase();
        purchase.setCar(car);
        purchase.setPrice(car.getPrice());
        purchase.setDate(new Date());
        purchasingService.addPurchase(purchase);

        return "redirect:/auto-komis/cars";
    }

    @RequestMapping(value = "/transactions", method = RequestMethod.GET)
    public String getAllTransactions(Model model) {
        List<Sale> allSales = sellingService.getAllSales();
        List<Purchase> allPurchases = purchasingService.getAllPurchases();

        List<Transaction> transactions = new LinkedList<>();
        transactions.addAll(allSales);
        transactions.addAll(allPurchases);
        transactions.sort(Comparator.comparing(Transaction::getId));
        model.addAttribute("transactions", transactions);

        model.addAttribute("text", "Panel Managera - zestawienie wszystkich transakcji");
        return "pages/transactionsPage";
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
