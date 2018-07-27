package sda.project.autoKomis.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "renouncement")
public class Renouncement extends Transaction implements Serializable {


}
