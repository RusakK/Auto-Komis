package sda.project.autoKomis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sda.project.autoKomis.model.Sale;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Integer> {
}
