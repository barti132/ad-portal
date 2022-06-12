package pl.bartoszsredzinski.application.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.bartoszsredzinski.application.backend.model.entity.Contact;

import java.util.List;

/**
 * Class description
 *
 * @author Bartosz Średziński
 * created on 03.06.2022
 */

public interface ContactRepository extends JpaRepository<Contact, Long>{
    @Query("select c from Contact c where lower(c.firstName) like lower(concat('%', :searchTerm, '%')) " +
            "or lower(c.lastName) like lower(concat('%', :searchTerm, '%'))")
    List<Contact> search(@Param("searchTerm") String searchTerm);
}
