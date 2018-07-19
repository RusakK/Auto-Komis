package sda.project.autoKomis.model.dto;

import groovy.transform.builder.Builder;
import sda.project.autoKomis.model.car.Car;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Builder
public class PurchaseDto {

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
    private Integer nip;
    @NotNull
    private Integer pesel;

    private Car car;

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
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

    public Integer getNip() {
        return nip;
    }

    public void setNip(Integer nip) {
        this.nip = nip;
    }

    public Integer getPesel() {
        return pesel;
    }

    public void setPesel(Integer pesel) {
        this.pesel = pesel;
    }
}
