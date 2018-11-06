package be.kdg.procesor.users.controllers;

import be.kdg.procesor.users.dto.AdminDTO;
import be.kdg.procesor.users.exceptions.UserException;
import be.kdg.procesor.users.model.Admin;
import be.kdg.procesor.users.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserRestController {
    private final UserService userService;
    private final ModelMapper modelMapper;

    public UserRestController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("users/{id}")
    public ResponseEntity<AdminDTO> loadUser(@PathVariable Long id) throws UserException {
        Admin admin = userService.load(id);
        return new ResponseEntity<>(modelMapper.map(admin, AdminDTO.class), HttpStatus.OK);
    }

    @PostMapping("/users")
    public ResponseEntity<AdminDTO> createUser(@RequestBody AdminDTO adminDTO) {
        Admin adminIn = modelMapper.map(adminDTO, Admin.class);
        Admin adminout = userService.save(adminIn);
        return new ResponseEntity<>(modelMapper.map(adminout, AdminDTO.class), HttpStatus.CREATED);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<AdminDTO> updateUser(@PathVariable Long id, @RequestParam("userName") String newUserName, @RequestParam("password") String newPassword) throws UserException {
        Admin admin = userService.update(id, newUserName, newPassword);
        return new ResponseEntity<>(modelMapper.map(admin, AdminDTO.class), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<AdminDTO> deleteUser(@PathVariable Long id) throws UserException {
        Admin admin = userService.delete(id);
        return new ResponseEntity<>(modelMapper.map(admin, AdminDTO.class), HttpStatus.ACCEPTED);
    }
}
