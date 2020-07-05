package six.app.entity;

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
    @JoinColumn(
            name = "extra_id",
            referencedColumnName = "x_id")
    public Extra extra;
}
