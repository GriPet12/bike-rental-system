package com.bike_rental.user_service.controllers;

import com.bike_rental.user_service.dto.UserDTO;
import com.bike_rental.user_service.dto.UserRequest;
import com.bike_rental.user_service.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    final private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<UserDTO> get(@RequestParam(required = false) String username, @RequestParam(required = false) String email){
        UserDTO user;
        if(username != null) {
            user = userService.getUser(username);
        } else if (email != null) {
            user = userService.getUserByEmail(email);
        }
        else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @GetMapping("{id}")
    public ResponseEntity<UserDTO> getById(@PathVariable("id") int userId){
        UserDTO user = userService.getUser(userId);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @PostMapping
    public ResponseEntity<Void> register(@RequestBody UserRequest user){
        userService.registerUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") int userId){
        userService.deleteUser(userId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("{id}/password")
    public ResponseEntity<Void> changePassword(@PathVariable("id") int userId, @RequestBody String newPassword){
        userService.changePassword(userId, newPassword);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
