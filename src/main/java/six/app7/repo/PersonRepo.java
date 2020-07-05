package six.app7.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import six.app7.entity.Person;
@Repository
public interface PersonRepo extends JpaRepository<Person, Long> {
}
