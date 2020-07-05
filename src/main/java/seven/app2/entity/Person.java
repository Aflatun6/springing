package seven.app2.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    @JsonBackReference
    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinTable(name = "person_phone",
            inverseJoinColumns = {@JoinColumn(name = "phone_id", referencedColumnName = "id")},
            joinColumns = {@JoinColumn(name = "person_id", referencedColumnName = "id")})
    Set<Phone> phones;

    public Person(String name, Set<Phone> phones) {
        this.name = name;
        this.phones = phones;
    }

    public Person(String name){
        this.name = name;
    }

}
