package be.kdg.procesor.users.persistence;

import be.kdg.procesor.users.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Admin, Long> {
    Optional<Admin> findByUserName(String userName);
}
