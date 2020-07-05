package six.app3.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Person {
    @Id
    @Column(name = "id")
    public int id;

    @Column(name = "name")
    public String name;

    @OneToOne
    @JoinTable(name = "person_extra",
            joinColumns =
                    {@JoinColumn(name = "personaaaaaaaa_id",
                            referencedColumnName = "id")},
            inverseJoinColumns = {
                    @JoinColumn(name = "extraaaaaaa_id",
                            referencedColumnName = "x_id")}
    )
    public Extra extra;
}
