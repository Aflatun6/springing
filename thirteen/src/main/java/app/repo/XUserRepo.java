package app.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import app.entity.db.XUser;

import java.util.Optional;

@Repository
public interface XUserRepo extends JpaRepository<XUser, Integer> {
    Optional<XUser> findByUsername(String name);
}
