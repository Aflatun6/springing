package six.app6.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
public class Book {

    @Id
    long id;

    String title;

    @ManyToMany(mappedBy="books")
    Set<Author> authors;

}
