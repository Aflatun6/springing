package six.app5.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Phone {

    @Id
    long id;

    String phone;

    @ManyToOne
    @JoinTable(name = "person_phone",
            joinColumns = {@JoinColumn(name = "phone_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "person_id", referencedColumnName = "p_id")})
    Person person;

}
