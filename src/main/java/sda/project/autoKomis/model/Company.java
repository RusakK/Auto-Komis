package sda.project.autoKomis.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "companies")
public class Company extends Client implements Serializable {


    private String name;

    private int regon;

    private int nip;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRegon() {
        return regon;
    }

    public void setRegon(int regon) {
        this.regon = regon;
    }

    public int getNip() {
        return nip;
    }

    public void setNip(int nip) {
        this.nip = nip;
    }
}
