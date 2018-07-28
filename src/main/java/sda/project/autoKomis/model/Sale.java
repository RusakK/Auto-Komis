package sda.project.autoKomis.model;

import javax.persistence.*;
import java.io.Serializable;

// sprzedaż z komisu do os. prywatnej/firmy

@Entity
@Table(name = "sales")
public class Sale extends Transaction implements Serializable {

    @ManyToOne
    @JoinColumn(name = "clientWhoBuy_Id")
    private Client client;

    private String seller;

    @Transient
    private Integer id = super.getId();

    @Transient
    private String classname = Sale.class.getSimpleName();


    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public String getClassname() {
        return classname;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }
}
