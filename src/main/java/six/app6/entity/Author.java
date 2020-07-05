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

    @ManyToMany
    @JoinTable(name = "author_book",
            inverseJoinColumns = {@JoinColumn(name = "book_id", referencedColumnName = "id")},
            joinColumns = {@JoinColumn(name = "author_id", referencedColumnName = "a_id")})
    public Set<Book> books;

}
