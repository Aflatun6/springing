package seven.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import seven.app.entity.Phone;

@Repository
public interface PhoneRepo extends JpaRepository<Phone,Long> {
}
