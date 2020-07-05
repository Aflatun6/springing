package six.app2.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Extra {
    @Id
    @Column(name = "x_id")
    public int id;

    @Column(name = "x_info")
    public String info;

    @OneToOne
    public Person person;
}
