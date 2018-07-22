package sda.project.autoKomis.service;

import org.springframework.stereotype.Service;
import sda.project.autoKomis.model.Purchase;
import sda.project.autoKomis.repository.ClientRepository;
import sda.project.autoKomis.repository.PurchaseRepository;
import sda.project.autoKomis.repository.carRepository.CarRepository;

import java.util.List;

@Service
public class DefaultPurchasingService implements PurchasingService {

    private CarRepository carRepository;
    private ClientRepository clientRepository;
    private PurchaseRepository purchaseRepository;

    public DefaultPurchasingService(CarRepository carRepository, ClientRepository clientRepository, PurchaseRepository purchaseRepository) {
        this.carRepository = carRepository;
        this.clientRepository = clientRepository;
        this.purchaseRepository = purchaseRepository;
    }


    @Override
    public List<Purchase> getAllPurchases() {
        return purchaseRepository.findAll();
    }

    @Override
    public Purchase getById(Integer purchaseId) {
        return purchaseRepository.findOne(purchaseId);
    }
}
