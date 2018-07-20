package sda.project.autoKomis.repository;

import org.springframework.data.repository.CrudRepository;
import sda.project.autoKomis.model.Employee;

public interface WorkerRepository extends CrudRepository<Employee, Integer> {


}
