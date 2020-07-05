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
public class Phone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    String number;
    @JsonManagedReference
    @ManyToMany(cascade = CascadeType.ALL,mappedBy = "phones",fetch = FetchType.LAZY)
    Set<Person> persons;

    public Phone(String number) {
        this.number = number;
    }


}
