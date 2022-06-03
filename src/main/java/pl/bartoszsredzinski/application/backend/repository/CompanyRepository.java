package pl.bartoszsredzinski.application.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.bartoszsredzinski.application.backend.model.entity.Company;

/**
 * Class description
 *
 * @author Bartosz Średziński
 * created on 03.06.2022
 */
public interface CompanyRepository extends JpaRepository<Company, Long>{
}
