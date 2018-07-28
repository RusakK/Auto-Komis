package sda.project.autoKomis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import sda.project.autoKomis.model.Person;
import sda.project.autoKomis.model.Sale;
import sda.project.autoKomis.model.dto.SaleDto;
import sda.project.autoKomis.service.CarDataService;
import sda.project.autoKomis.service.SellingService;

import javax.validation.Valid;
import java.util.List;


@Controller
@RequestMapping("/auto-komis/online/sales")
public class SellingController {


    private final SellingService sellingService;
    private final CarDataService carDataService;

    @Autowired
    public SellingController(SellingService sellingService, CarDataService carDataService) {
        this.sellingService = sellingService;
        this.carDataService = carDataService;
    }

    @PreAuthorize("hasAnyRole('MANAGER', 'ADMIN')")
    @RequestMapping(method = RequestMethod.GET)
    public String showSales(Model model) {
        List<Sale> allSales = sellingService.getAllSales();
        String text = "Panel Managera - sprzedaż";
        model.addAttribute("text", text);
        Integer sumabrutto = 0;
        double sumanetto = 0;
        double zysknetto = 0;
        double podatek = 0;

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

    @PreAuthorize("hasAnyRole('EMPLOYEE', 'MANAGER', 'ADMIN')")
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
        person.setPesel(Integer.parseInt(saleDto.getPesel()));

        sellingService.sellCar(saleDto.getCarId(), person, saleDto.getPrice());
        return "redirect:/auto-komis/online/cars";
    }

    @PreAuthorize("hasAnyRole('EMPLOYEE', 'MANAGER', 'ADMIN')")
    @RequestMapping("/sales/{id}")
    public String saleDetails(@PathVariable("id") Integer saleId,
                              Model model) {
        Sale sale = sellingService.getById(saleId);

        if (sale != null) {
            model.addAttribute("sale", sale);
        }
        return "pages/saleDetailsPage";
    }


}
