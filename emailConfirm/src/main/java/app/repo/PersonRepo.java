package app.repo;

import app.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepo extends JpaRepository<Person, Integer> {

    Person findByEmailIdIgnoreCase(String emailId);

}
