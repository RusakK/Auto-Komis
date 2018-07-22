package sda.project.autoKomis.service;

import sda.project.autoKomis.model.Client;
import sda.project.autoKomis.model.Sale;

import java.util.List;


public interface SellingService {

    Sale sellCar(Integer carId, Client Client, Integer price);

    List<Sale> getAllSales();

    Sale getById(Integer saleId);
}
