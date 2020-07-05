package six.app7.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class Phone {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    long id;

    String number;

    public Phone(String n){
        number = n;
    }

}
