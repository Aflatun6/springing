package seven.app.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Phone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    String number;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "person_phone",
            joinColumns = {@JoinColumn(name = "phone_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "person_id", referencedColumnName = "id")})
    Person person;

    public Phone(String number) {
        this.number = number;
    }

    public Phone(String number, Person person) {
        this(number);
        this.person = person;
    }

}
