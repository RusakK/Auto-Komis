package sda.project.autoKomis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sda.project.autoKomis.model.Client;
import sda.project.autoKomis.model.Sale;
import sda.project.autoKomis.model.car.Car;
import sda.project.autoKomis.repository.ClientRepository;
import sda.project.autoKomis.repository.SaleRepository;
import sda.project.autoKomis.repository.carRepository.CarRepository;

import java.util.Date;
import java.util.List;

@Service
public class DefaultSellingService implements SellingService {


    private CarRepository carRepository;
    private ClientRepository clientRepository;
    private SaleRepository saleRepository;

    @Autowired
    public DefaultSellingService(CarRepository carRepository, ClientRepository clientRepository, SaleRepository saleRepository) {
        this.carRepository = carRepository;
        this.clientRepository = clientRepository;
        this.saleRepository = saleRepository;
    }

    @Override
    public Sale sellCar(Integer soldCarId, Client client, Integer price) {
        Car soldCar = carRepository.findOne(soldCarId);
        soldCar.setSold(true);
        carRepository.save(soldCar);

        Client persistedClient = clientRepository.findByPesel(client.getPesel()).orElseGet(() -> clientRepository.save(client));

        Sale sale = new Sale();
        sale.setCar(soldCar);
        sale.setClient(persistedClient);
        sale.setDate(new Date());
        sale.setPrice(price);
        return saleRepository.save(sale);
    }

    @Override
    public List<Sale> getAllSales() {
        return saleRepository.findAll();
    }

    @Override
    public Sale getById(Integer saleId) {
        return saleRepository.findOne(saleId);
    }

}
