package sda.project.autoKomis.model;

import org.springframework.format.annotation.DateTimeFormat;
import sda.project.autoKomis.model.preparedModel.BaseModel;
import sda.project.autoKomis.model.preparedModel.Role;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "workers")
public class Worker extends BaseModel {

    private String firstname;

    private String lastname;

    private String address;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date employmentDate;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "worker_role",
            joinColumns = @JoinColumn(name = "workerId"),
            inverseJoinColumns = @JoinColumn(name = "roleId"))
    private List<Role> rolesType;


    public List<Role> getRolesType() {
        return rolesType;
    }

    public void setRolesType(List<Role> rolesType) {
        this.rolesType = rolesType;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getEmploymentDate() {
        return employmentDate;
    }

    public void setEmploymentDate(Date employmentDate) {
        this.employmentDate = employmentDate;
    }
}
