package pl.bartoszsredzinski.application.backend.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * Class description
 *
 * @author Bartosz Średziński
 * created on 03.06.2022
 */
@Entity
@NoArgsConstructor
@Getter
@Setter
public class Contact extends AbstractEntity implements Cloneable{

    public enum Status{
        ImportedLead, NotContacted, Contacted, Customer, ClosedLost
    }

    @NotNull
    @NotEmpty
    private String firstName;

    @NotNull
    @NotEmpty
    private String lastName;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @Enumerated(EnumType.STRING)
    @NotNull
    private Contact.Status status;

    @Email
    @NotNull
    @NotEmpty
    private String email;

   @Override
   public String toString(){
       return firstName + " " + lastName;
   }
}
