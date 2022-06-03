package pl.bartoszsredzinski.application.backend.model.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Class description
 *
 * @author Bartosz Średziński
 * created on 03.06.2022
 */

@MappedSuperclass
@Data
public abstract class AbstractEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
}
