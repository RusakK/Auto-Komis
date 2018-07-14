package sda.project.autoKomis.model.preparedModel;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Manufacturer extends BaseModel {

    private String manufacturerName;

    @OneToMany(mappedBy = "manufacturer")
    private List<Model> models;

    public String getManufacturerName() {
        return manufacturerName;
    }

    public void setManufacturerName(String manufacturerName) {
        this.manufacturerName = manufacturerName;
    }

    public List<Model> getModels() {
        return models;
    }

    public void setModels(List<Model> models) {
        this.models = models;
    }
}
