package app.repo;

import app.entity.db.XUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface XUserRepo extends JpaRepository<XUser, Integer> {
}
