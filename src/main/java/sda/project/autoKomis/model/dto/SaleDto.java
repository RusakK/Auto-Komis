package sda.project.autoKomis.model.dto;

import groovy.transform.builder.Builder;
import sda.project.autoKomis.model.car.Car;
import sda.project.autoKomis.model.car.Manufacturer;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Builder
public class SaleDto {

    private Manufacturer manufacturer;

    @NotNull
    @Min(1)
    private Integer carId;
    @NotNull
    @Min(value = 5000, message = "Cena zbyt niska... Zmiaeń kwotę na wyższą!")
    private Integer price;
    @NotNull
    @Size(min = 1, max = 200)
    private String firstname;
    @NotNull
    @Size(min = 1, max = 200)
    private String lastname;
    @NotNull
    private String address;
    @NotNull
    @Size(min = 9, max = 9)
    private String nip;
    @NotNull
    @Size(min = 11, max = 11)
    private String pesel;

    private Car car;

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @NotNull
    public String getNip() {
        return nip;
    }

    public void setNip(@NotNull String nip) {
        this.nip = nip;
    }

    @NotNull
    public String getPesel() {
        return pesel;
    }

    public void setPesel(@NotNull String pesel) {
        this.pesel = pesel;
    }
}
