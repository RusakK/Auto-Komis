package sda.project.autoKomis.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "employees")
public class Employee extends BaseModel {

    @OneToOne
    @JoinColumn(name = "userId")
    private User user;

    private String firstname;

    private String lastname;

    private String address;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date employmentDate;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "employee_roles",
            joinColumns = @JoinColumn(name = "employeeId"),
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
