package sda.project.autoKomis.model.car;

import sda.project.autoKomis.model.BaseModel;

import javax.persistence.Entity;

@Entity
public class BodyType extends BaseModel {

    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
