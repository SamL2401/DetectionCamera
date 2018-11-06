package be.kdg.procesor.users.services;

import be.kdg.procesor.users.exceptions.UserException;
import be.kdg.procesor.users.model.Admin;
import be.kdg.procesor.users.persistence.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Admin save(Admin admin) {
        return userRepository.save(admin);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Admin> optionalAdmin = userRepository.findByUserName(username);
        if (optionalAdmin.isPresent()) {
            Admin admin = optionalAdmin.get();
            return User.withDefaultPasswordEncoder()
                    .username(admin.getUserName())
                    .password(admin.getPassword())
                    .roles("ADMIN")
                    .build();
        } else {
            throw new UsernameNotFoundException("not found");
        }
    }

    public Admin load(Long id) throws UserException {
        Optional<Admin> optionalAdmin = userRepository.findById(id);
        if (optionalAdmin.isPresent()) {
            return optionalAdmin.get();
        }
        throw new UserException("Admin not found");
    }

    public Admin update(Long id, String userName, String password) throws UserException {
        Admin adminIn = load(id);
        adminIn.setUserName(userName);
        adminIn.setPassword(password);
        return save(adminIn);
    }

    public Admin delete(Long id) throws UserException {
        Admin admin = load(id);
        userRepository.delete(admin);
        return admin;
    }
}
