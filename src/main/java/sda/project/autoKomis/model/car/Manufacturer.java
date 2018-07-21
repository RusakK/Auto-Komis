package sda.project.autoKomis.model.car;

import sda.project.autoKomis.model.BaseModel;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "manufacturers")
public class Manufacturer extends BaseModel {

    private String name;

    private String image;

    @OneToMany(mappedBy = "manufacturer", cascade = CascadeType.ALL)
    private List<Model> models;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<Model> getModels() {
        return models;
    }

    public void setModels(List<Model> models) {
        this.models = models;
    }
}
