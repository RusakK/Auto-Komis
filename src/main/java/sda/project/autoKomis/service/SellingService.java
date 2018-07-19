package sda.project.autoKomis.service;


import sda.project.autoKomis.model.Client;
import sda.project.autoKomis.model.Purchase;
import sda.project.autoKomis.model.Trader;
import sda.project.autoKomis.model.car.Car;

public interface SellingService {

    Purchase sellCar(Integer carId, Client Client, Trader trader, Integer price);


}
