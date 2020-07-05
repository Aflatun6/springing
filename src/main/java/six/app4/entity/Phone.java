package six.app4.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@Entity
public class Phone {

    @Id
    long id;

    String phone;

    @ManyToOne
    @JoinColumn(name = "person_id")
    Person person;

}
