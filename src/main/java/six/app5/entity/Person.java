package six.app5.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Set;

@Data
@Entity
public class Person {
    @Id
    @Column(name = "p_id")
    long id;

    @Column(name = "p_name")
    String name;

    @OneToMany(mappedBy = "person")
    public Set<Phone> phone;

}
