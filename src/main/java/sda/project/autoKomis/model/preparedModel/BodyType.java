package sda.project.autoKomis.model.preparedModel;

import javax.persistence.Entity;

@Entity
public class BodyType extends BaseModel {

    private String bodyType;

    public String getBodyType() {
        return bodyType;
    }

    public void setBodyType(String bodyType) {
        this.bodyType = bodyType;
    }
}
