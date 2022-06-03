package pl.bartoszsredzinski.application.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.bartoszsredzinski.application.backend.model.entity.Contact;

/**
 * Class description
 *
 * @author Bartosz Średziński
 * created on 03.06.2022
 */

public interface ContactRepository extends JpaRepository<Contact, Long>{
}
