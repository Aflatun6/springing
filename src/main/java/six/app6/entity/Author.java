package six.app6.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
public class Author {
    @Id
    @Column(name = "a_id")
    long id;

    @Column(name = "a_name")
    String name;

    @ManyToMany(mappedBy = "authors")
    public Set<Book> books;

}
