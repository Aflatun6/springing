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
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    String name;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "person")
    Set<Phone> phones;

    public Person(String name, Set<Phone> phones) {
        this.name = name;
        this.phones = phones;
    }

    public Person(String name){
        this.name = name;
    }

}
