package sda.project.autoKomis.service;


import sda.project.autoKomis.model.Client;
import sda.project.autoKomis.model.Purchase;

public interface SellingService {

    Purchase sellCar(Integer carId, Client Client, Integer price);


}
