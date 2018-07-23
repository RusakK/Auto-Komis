package sda.project.autoKomis.model;

import javax.persistence.*;
import java.io.Serializable;

// zakup do komisu

@Entity
@Table(name = "purchases")
public class Purchase extends Transaction implements Serializable {


    @ManyToOne
    @JoinColumn(name = "clientWhoSold_Id")
    private Client clientWhoSold;

    @Transient
    private String classname = Purchase.class.getSimpleName();

    public String getClassname() {
        return classname;
    }

    public Client getClientWhoSold() {
        return clientWhoSold;
    }

    public void setClientWhoSold(Client clientWhoSold) {
        this.clientWhoSold = clientWhoSold;
    }
}
