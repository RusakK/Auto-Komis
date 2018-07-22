package sda.project.autoKomis.service;


import sda.project.autoKomis.model.Purchase;

import java.util.List;

public interface PurchasingService {


    List<Purchase> getAllPurchases();


    Purchase getById(Integer purchaseId);
}
