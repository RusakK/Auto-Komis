package sda.project.autoKomis.model;

import javax.persistence.*;
import java.io.Serializable;

// sprzedaż z komisu do os. prywatnej/firmy

@Entity
@Table(name = "sales")
public class Sale extends Transaction implements Serializable {

    @ManyToOne
    @JoinColumn(name = "clientWhoBuy_Id")
    private Client clientWhoBuy;

    private String seller;

    @Transient
    private String classname = Sale.class.getSimpleName();

    public String getClassname() {
        return classname;
    }

    public Client getClientWhoBuy() {
        return clientWhoBuy;
    }

    public void setClientWhoBuy(Client clientWhoBuy) {
        this.clientWhoBuy = clientWhoBuy;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }
}
