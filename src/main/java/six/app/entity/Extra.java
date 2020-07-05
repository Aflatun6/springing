package six.app.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Data
@Entity
public class Extra {
    @Id
    @Column(name = "x_id")
    public int id;

    @Column(name = "x_info")
    public String info;

    @OneToOne(mappedBy = "extra")
    public Person person;
}
