package omarp.pruebatecnica.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import omarp.pruebatecnica.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByUsername(String username);
}