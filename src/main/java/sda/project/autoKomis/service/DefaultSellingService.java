package sda.project.autoKomis.service;

import org.springframework.stereotype.Service;
import sda.project.autoKomis.model.Client;
import sda.project.autoKomis.model.Purchase;
import sda.project.autoKomis.model.car.Car;
import sda.project.autoKomis.repository.ClientRepository;
import sda.project.autoKomis.repository.PurchaseRepository;
import sda.project.autoKomis.repository.carRepository.CarRepository;

import java.util.Date;

@Service
public class DefaultSellingService implements SellingService {

    private CarRepository carRepository;
    private ClientRepository clientRepository;
    private PurchaseRepository purchaseRepository;

    public DefaultSellingService(CarRepository carRepository, ClientRepository clientRepository, PurchaseRepository purchaseRepository) {
        this.carRepository = carRepository;
        this.clientRepository = clientRepository;
        this.purchaseRepository = purchaseRepository;
    }

    @Override
    public Purchase sellCar(Integer soldCarId, Client client, Integer price) {
        Car soldCar = carRepository.findOne(soldCarId);
        soldCar.setSold(true);
        carRepository.save(soldCar);

        Client persistedClient = clientRepository.findByPesel(client.getPesel()).orElseGet(() -> clientRepository.save(client));

        Purchase purchase = new Purchase();
        purchase.setCar(soldCar);
        purchase.setClient(persistedClient);
        purchase.setDate(new Date());
        purchase.setPrice(price);
        return purchaseRepository.save(purchase);
    }
}
