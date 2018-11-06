package be.kdg.procesor.users;

import be.kdg.procesor.users.model.Admin;
import be.kdg.procesor.users.services.UserService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Class is responsible for creating a Default Admin
 */
@Component
public class CreateDefaultAdmin {
    private final UserService userService;

    public CreateDefaultAdmin(UserService userService) {
        this.userService = userService;
    }

    @PostConstruct
    public void createAdmin() {
        Admin admin = new Admin("sam", "pwd");
        userService.save(admin);
    }
}
