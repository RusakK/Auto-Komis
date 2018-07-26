package sda.project.autoKomis.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
public class Role extends BaseModel {

    private String roleType;


    public String getRoleType() {
        return roleType;
    }




}
