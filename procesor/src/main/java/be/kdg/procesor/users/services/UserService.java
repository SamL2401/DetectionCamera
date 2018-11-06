package be.kdg.procesor.users.services;

import be.kdg.procesor.users.model.Admin;
import be.kdg.procesor.users.persistence.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Admin save(Admin admin) {
        return userRepository.save(admin);
    }

    @PostConstruct
    private void createAdmin() {
        Admin admin = new Admin("sammy", "pwd", new ArrayList<GrantedAuthority>());

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
