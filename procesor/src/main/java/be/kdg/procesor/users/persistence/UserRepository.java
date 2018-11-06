package be.kdg.procesor.users.persistence;

import be.kdg.procesor.users.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Admin,Long> {

}
