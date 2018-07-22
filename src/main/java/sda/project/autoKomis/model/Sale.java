package sda.project.autoKomis.model;

import org.springframework.format.annotation.DateTimeFormat;
import sda.project.autoKomis.model.car.Car;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "sales")
public class Sale extends BaseModel implements Serializable {

    @ManyToOne
    @JoinColumn(name = "carId")
    private Car car;
    @ManyToOne
    @JoinColumn(name = "clientId")
    private Client client;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;

    private Integer price;

    private String seller;

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }
}
