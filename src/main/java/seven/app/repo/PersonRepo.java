package seven.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import seven.app.entity.Person;

@Repository
public interface PersonRepo extends JpaRepository<Person,Long> {
}
