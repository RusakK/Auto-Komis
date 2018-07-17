package sda.project.autoKomis.model.preparedModel;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Manufacturer extends BaseModel {

    private String name;

    @OneToMany(mappedBy = "manufacturer", cascade = CascadeType.ALL)
    private List<Model> models;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Model> getModels() {
        return models;
    }

    public void setModels(List<Model> models) {
        this.models = models;
    }
}
