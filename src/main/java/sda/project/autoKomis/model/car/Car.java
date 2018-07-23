package sda.project.autoKomis.model.car;

import sda.project.autoKomis.model.BaseModel;
import sda.project.autoKomis.model.Client;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "cars")
public class Car extends BaseModel implements Serializable {

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "manufacturerId")
    private Manufacturer manufacturer;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "modelId")
    private Model model;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "bodyTypeId")
    private BodyType bodyType;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "fuelId")
    private Fuel fuel;

    private String bodyNumber;

    private Integer productionYear;

    private String insuranceNumber;

    private String documentNumber;

    private Integer mileage;

    private String engine;

    private String power;

    private Transmission transmission;

    private String description;

    private Integer driverTestCount;

    private Integer price;

    private boolean sold;

    @OneToOne
    @JoinColumn(name = "owner_clientId")
    private Client owner;

    private String image;


    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public BodyType getBodyType() {
        return bodyType;
    }

    public void setBodyType(BodyType bodyType) {
        this.bodyType = bodyType;
    }

    public Fuel getFuel() {
        return fuel;
    }

    public void setFuel(Fuel fuel) {
        this.fuel = fuel;
    }

    public String getBodyNumber() {
        return bodyNumber;
    }

    public void setBodyNumber(String bodyNumber) {
        this.bodyNumber = bodyNumber;
    }

    public Integer getProductionYear() {
        return productionYear;
    }

    public void setProductionYear(Integer productionYear) {
        this.productionYear = productionYear;
    }

    public String getInsuranceNumber() {
        return insuranceNumber;
    }

    public void setInsuranceNumber(String insuranceNumber) {
        this.insuranceNumber = insuranceNumber;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public Integer getMileage() {
        return mileage;
    }

    public void setMileage(Integer mileage) {
        this.mileage = mileage;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }

    public Transmission getTransmission() {
        return transmission;
    }

    public void setTransmission(Transmission transmission) {
        this.transmission = transmission;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getDriverTestCount() {
        return driverTestCount;
    }

    public void setDriverTestCount(Integer driverTestCount) {
        this.driverTestCount = driverTestCount;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public boolean isSold() {
        return sold;
    }

    public void setSold(boolean sold) {
        this.sold = sold;
    }

    public Client getOwner() {
        return owner;
    }

    public void setOwner(Client owner) {
        this.owner = owner;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
