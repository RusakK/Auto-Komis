package sda.project.autoKomis.model.dto;

import groovy.transform.builder.Builder;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Builder
public class CarDto {


    @NotNull(message = "Wybierz marke samochodu")
    private Integer manufacturer;
    @Size(min = 1, message = "Wprowadź model samochodu")
    private String model;
    @NotNull(message = "Wybierz nadwozie")
    private Integer bodyType;
    @NotNull(message = "Wybierz rodzaj paliwa")
    private Integer fuel;
    @Size(min = 3, message = "Wprowadź numer nadwozia ")
    private String bodyNumber;
    @NotNull(message = "Wprowadź rok produkcji")
    @Min(value = 1901, message = "Takie starego samochodu nie kupujemy")
    @Max(value = 2018, message = "Auto z przyszłości??")
    private Integer productionYear;
    @Size(min = 3, message = "Wprowadź numer ubezpieczenia OC")
    private String insuranceNumber;
    @Size(min = 3, message = "Wprowadź numer dowodu rejestracyjnego")
    private String documentNumber;
    @NotNull(message = "Wprowadź aktualny przebieg")
    private Integer mileage;
    @Size(min = 1, message = "Wproadź pojemność silnika")
    private String engine;
    @Size(min = 1, message = "Wprowadź moc silnika (KM) ")
    private String power;
    @Length(max = 100)
    @Size(max = 25, message = "Zbyt długi tekst")
    private String description;
    @NotNull(message = "Wprowadź kwote")
    private Integer price;
    private int transmission;


    public Integer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Integer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getBodyType() {
        return bodyType;
    }

    public void setBodyType(Integer bodyType) {
        this.bodyType = bodyType;
    }

    public Integer getFuel() {
        return fuel;
    }

    public void setFuel(Integer fuel) {
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

    public int getTransmission() {
        return transmission;
    }

    public void setTransmission(int transmission) {
        this.transmission = transmission;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
