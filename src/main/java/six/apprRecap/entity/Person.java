package six.apprRecap.entity;

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
//////////////////////////////
    //  THIRD COLUMN IN PERSON
//    @OneToOne
//    @JoinColumn(name = "extra_id",
//    referencedColumnName = "x_id")
//    public Extra extra;
    //////////////////////////////////
    // ONE COMMON SHARED ID
//    @OneToOne(mappedBy = "person")
//    public Extra extra;
    /////////////////////////////////////
    // THIRD TABLE
//    @OneToOne
//    @JoinTable(name = "p_ex_4",
//            joinColumns = {@JoinColumn(name = "p_id", referencedColumnName = "id")},
//            inverseJoinColumns = {@JoinColumn(name = "ex_id", referencedColumnName = "x_id")})
//    public Extra extra;
}
