package six.app7.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import six.app7.entity.Phone;
@Repository
public interface PhoneRepo extends JpaRepository<Phone, Long> {
}
