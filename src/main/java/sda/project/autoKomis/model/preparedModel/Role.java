package sda.project.autoKomis.model.preparedModel;

import sda.project.autoKomis.model.User;
import sda.project.autoKomis.model.Worker;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
public class Role extends BaseModel {

    private String roleType;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "worker_role",
            joinColumns = @JoinColumn(name = "roleId"),
            inverseJoinColumns = @JoinColumn(name = "workerId"))
    private List<Worker> workers;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

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
