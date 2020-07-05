package six.apprRecap.entity;

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
    //////////////////////////////////////////
    //  THIRD COLUMN IN PERSON
//    @OneToOne(mappedBy = "extra")
//    public Person person;
    //////////////////////////////////////////
    // ONE COMMON SHARED ID
//    @OneToOne
//    @MapsId
//    public Person person;

    //////////////////////////
    // THIRD TABLE
//    @OneToOne(mappedBy = "extra")
//    public Person person;
}
