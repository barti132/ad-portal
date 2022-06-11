package pl.bartoszsredzinski.application.backend.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.LinkedList;
import java.util.List;

/**
 * Class description
 *
 * @author Bartosz Średziński
 * created on 03.06.2022
 */
@Entity
@AllArgsConstructor
@Getter
@Setter
public class Company extends AbstractEntity{
    private String name;

    @OneToMany(mappedBy = "company", fetch = FetchType.EAGER)
    private List<Contact> employees = new LinkedList<>();

    public Company(){
    }

    public Company(String name){
        this.name = name;
    }
}
