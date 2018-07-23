package sda.project.autoKomis.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "clients")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "CLIENT_TYPE", discriminatorType = DiscriminatorType.STRING)
public abstract class Client extends BaseModel implements Serializable {

    @OneToOne
    @JoinColumn(name = "userId")
    private User user;

    private String address;


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
