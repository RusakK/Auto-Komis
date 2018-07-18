package sda.project.autoKomis.model.car;

import sda.project.autoKomis.model.BaseModel;

import javax.persistence.Entity;

@Entity
public class Fuel extends BaseModel {

    private String fuelType;

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }
}
