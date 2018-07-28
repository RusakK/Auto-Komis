package sda.project.autoKomis.model;

import javax.persistence.*;
import java.io.Serializable;

// zakup do komisu

@Entity
@Table(name = "purchases")
public class Purchase extends Transaction implements Serializable {


    @ManyToOne
    @JoinColumn(name = "clientWhoSold_Id")
    private Client client;

    @Transient
    private String classname = Purchase.class.getSimpleName();

    @Transient
    private Integer id = super.getId();


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
}
