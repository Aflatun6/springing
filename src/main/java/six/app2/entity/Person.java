package six.app2.entity;

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
    public Extra extra;
}
