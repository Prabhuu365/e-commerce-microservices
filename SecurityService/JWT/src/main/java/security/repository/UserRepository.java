package security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import security.entity.UserCredentials;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserCredentials,Integer> {
    Optional<UserCredentials> findByUsername(String username);
}
