package sda.project.autoKomis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import sda.project.autoKomis.model.Purchase;
import sda.project.autoKomis.service.PurchasingService;

import java.util.List;

@Controller
@RequestMapping("/auto-komis/purchases")
public class PurchaseController {

    private final PurchasingService purchasingService;


    @Autowired
    public PurchaseController(PurchasingService purchasingService) {
        this.purchasingService = purchasingService;

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
}
