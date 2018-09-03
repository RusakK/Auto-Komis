package sda.project.autoKomis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import sda.project.autoKomis.model.Person;
import sda.project.autoKomis.model.Purchase;
import sda.project.autoKomis.model.Sale;
import sda.project.autoKomis.model.Transaction;
import sda.project.autoKomis.model.car.Car;
import sda.project.autoKomis.model.car.Transmission;
import sda.project.autoKomis.model.dto.CarDto;
import sda.project.autoKomis.model.dto.SaleDto;
import sda.project.autoKomis.service.CarDataService;
import sda.project.autoKomis.service.PurchasingService;
import sda.project.autoKomis.service.SellingService;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.*;

@Controller
@PreAuthorize("hasAnyRole('MANAGER', 'ADMIN')")
@RequestMapping("/auto-komis/online/transactions")
public class TransactionsController {

    private final PurchasingService purchasingService;
    private final CarDataService carDataService;
    private final SellingService sellingService;


    @Autowired
    public TransactionsController(PurchasingService purchasingService, CarDataService carDataService, SellingService sellingService) {
        this.purchasingService = purchasingService;

        this.carDataService = carDataService;
        this.sellingService = sellingService;
    }


    @RequestMapping(value = "/purchases", method = RequestMethod.GET)
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

    @GetMapping("/{id}")
    public String transactionDetails(@PathVariable("id") Integer transactionId, Model model) {

        Purchase byId = purchasingService.getById(transactionId);
        Sale byId1 = sellingService.getById(transactionId);
        if (byId != null) {
            model.addAttribute("transaction", byId);
        }
        if (byId1 != null) {
            model.addAttribute("transaction", byId1);
        }

        return "pages/transactionDetailsPage";
    }

    @PreAuthorize("hasAnyRole('CLIENT', 'EMPLOYEE')")
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

        return "redirect:/auto-komis/online/cars";
    }

    @RequestMapping(method = RequestMethod.GET)
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

    @RequestMapping(value = "/sales", method = RequestMethod.GET)
    public String showSales(Model model) {
        List<Sale> allSales = sellingService.getAllSales();
        String text = "Panel Managera - sprzedaż";
        model.addAttribute("text", text);
        Integer sumabrutto = 0;
        double sumanetto = 0;
        double zysknetto = 0;
        double podatek = 0.00;

        for (Sale sale : allSales) {
            sumabrutto += sale.getPrice();
            sumanetto += sale.getPrice() - (sale.getPrice() * 0.19);
            zysknetto += (sale.getPrice() - (sale.getPrice() * 0.19)) - sale.getCar().getPrice();
            podatek += sale.getPrice() * 0.19;
        }


        model.addAttribute("sumabrutto", sumabrutto);
        model.addAttribute("sumanetto", sumanetto);
        model.addAttribute("zysknetto", zysknetto);
        model.addAttribute("sumapodatek", podatek);

        model.addAttribute("allSales", allSales);
        return "pages/salesPage";
    }

    @PreAuthorize("hasAnyRole('EMPLOYEE')")
    @PostMapping
    public String sellCar(@Valid @ModelAttribute("saleDto") SaleDto saleDto,
                          BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "pages/sellCarPage";
        }
        saleDto.setCar(carDataService.getById(saleDto.getCarId()));
        Person person = new Person();
        person.setFirstname(saleDto.getFirstname());
        person.setLastname(saleDto.getLastname());
        person.setAddress(saleDto.getAddress());
        person.setPesel(saleDto.getPesel());

        sellingService.sellCar(saleDto.getCarId(), person, saleDto.getPrice());
        return "redirect:/auto-komis/online/cars";
    }

    @PreAuthorize("hasAnyRole('EMPLOYEE')")
    @RequestMapping("/{id}")
    public String saleDetails(@PathVariable("id") Integer saleId,
                              Model model) {
        Sale sale = sellingService.getById(saleId);

        if (sale != null) {
            model.addAttribute("sale", sale);
        }
        return "pages/saleDetailsPage";
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
