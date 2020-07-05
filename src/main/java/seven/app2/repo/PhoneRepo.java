package seven.app2.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import seven.app2.entity.Phone;

@Repository
public interface PhoneRepo extends JpaRepository<Phone,Long> {
}
