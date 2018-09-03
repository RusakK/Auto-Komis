package sda.project.autoKomis.model;

import org.springframework.format.annotation.DateTimeFormat;
import sda.project.autoKomis.model.car.Car;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "TRANSACTION_TYPE", discriminatorType = DiscriminatorType.STRING)
public abstract class Transaction extends BaseModel implements Serializable {

    @ManyToOne
    @JoinColumn(name = "carId")
    private Car car;
    
    private Date date;

    private Integer price;


    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
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
}
