package vn.hoidanit.jobhunter.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import vn.hoidanit.jobhunter.domain.DTO.RestUser;
import vn.hoidanit.jobhunter.domain.User;
import vn.hoidanit.jobhunter.service.UserService;
import vn.hoidanit.jobhunter.utils.error.IdInvalidExeption;

import java.util.List;

@RestController
public class UserController {
    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users")
    public ResponseEntity<RestUser> CreateNewUser(@RequestBody User datauser) {




        return ResponseEntity.status(HttpStatus.CREATED).body(this.userService.handleSaveuUser(datauser));
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUser(@PathVariable long id) {
        User user = this.userService.handleGetUserById(id);
        if (user != null)
            return ResponseEntity.status(HttpStatus.OK).body(user);
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUser() {

        List<User> listUser = this.userService.getAllUsers();
        return ResponseEntity.status(HttpStatus.OK).body(listUser);

    }


    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable long id) throws IdInvalidExeption {
        if (id >= 1500) {
            throw new IdInvalidExeption("ID not Exits");
        }
        this.userService.handleDeleteUser(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("User deleted successfully");


    }

    @PutMapping("/users")
    public ResponseEntity<User> updateUser(@RequestBody User datauser) {
        User userUpdate = this.userService.handleUpdateUser(datauser);
        return ResponseEntity.status(HttpStatus.OK).body(userUpdate);
    }
}
