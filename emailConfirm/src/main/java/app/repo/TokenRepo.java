package app.repo;

import app.entity.ConfirmationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepo extends JpaRepository<ConfirmationToken, Integer> {
    ConfirmationToken findByConfirmationToken(String ConfirmationToken);
}
