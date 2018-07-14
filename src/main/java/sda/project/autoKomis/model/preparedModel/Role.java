package sda.project.autoKomis.model.preparedModel;

import sda.project.autoKomis.model.Worker;

import javax.persistence.*;
import java.util.List;

@Entity
public class Role extends BaseModel{

    private String roleType;

    @ManyToMany
    @JoinTable(
            name = "worker_role",
            joinColumns = @JoinColumn(name = "roleId"),
            inverseJoinColumns = @JoinColumn(name = "workerId"))
    private List<Worker> workers;


    public String getRoleType() {
        return roleType;
    }

    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }

    public List<Worker> getWorkers() {
        return workers;
    }

    public void setWorkers(List<Worker> workers) {
        this.workers = workers;
    }
}
