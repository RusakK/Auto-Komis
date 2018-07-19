package sda.project.autoKomis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import sda.project.autoKomis.model.Client;
import sda.project.autoKomis.model.Trader;
import sda.project.autoKomis.model.dto.PurchaseDto;
import sda.project.autoKomis.service.SellingService;

import javax.validation.Valid;


@Controller
@RequestMapping("/auto-komis/purchases")
public class SellingController {

    private final SellingService sellingService;

    public SellingController(SellingService sellingService) {
        this.sellingService = sellingService;
    }

    @PostMapping
    public String sellCar(@Valid @ModelAttribute("purchaseDto") PurchaseDto purchaseDto,
                          BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "pages/sellCarPage";
        }
        Client client = new Client();
        client.setFirstname(purchaseDto.getFirstname());
        client.setLastname(purchaseDto.getLastname());
        client.setAddress(purchaseDto.getAddress());
        client.setNip(purchaseDto.getNip());
        client.setPesel(purchaseDto.getPesel());
        Trader trader = new Trader();
        trader.setClient(true);
        sellingService.sellCar(purchaseDto.getCarId(), client, trader, purchaseDto.getPrice());
        return "redirect:/auto-komis/cars";
    }


}
