package sda.project.autoKomis.service;

import sda.project.autoKomis.model.Person;
import sda.project.autoKomis.model.Sale;

import java.util.List;


public interface SellingService {

    Sale sellCar(Integer carId, Person person, Integer price);

    List<Sale> getAllSales();

    Sale getById(Integer saleId);


}
