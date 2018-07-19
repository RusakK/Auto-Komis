package sda.project.autoKomis.model;

import java.io.Serializable;

public class Trader extends BaseModel implements Serializable {

    // Klasa w przygotowaniu - tu będą sprzedawacy, olbo pracownik jako sprzedawca albo klient jeśli sprzedaje do komisu
    private boolean Worker;

    private boolean client;

    public boolean isWorker() {
        return Worker;
    }

    public void setWorker(boolean worker) {
        Worker = worker;
    }

    public boolean isClient() {
        return client;
    }

    public void setClient(boolean client) {
        this.client = client;
    }
}
