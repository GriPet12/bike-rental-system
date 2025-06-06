package com.bike_rental.user_service.controllers;

import com.bike_rental.user_service.dto.UserDTO;
import com.bike_rental.user_service.dto.UserRequest;
import com.bike_rental.user_service.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/user")
public class UserController {

    final private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<UserDTO> get(@RequestParam(required = false) String username,
                                       @RequestParam(required = false) String email){
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
    public ResponseEntity<Void> register(@AuthenticationPrincipal UserDetails userDetails,
                                         @RequestBody UserRequest user){
        if(userDetails != null) {
            String role = userDetails.getAuthorities().stream().findFirst().map(GrantedAuthority::getAuthority).orElse(null);
            //Зареєструвати адміна може тільки адмін
            if (Objects.equals(user.role().toString(), "ADMIN") && (!Objects.equals(role, "ADMIN"))) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            } else {
                userService.registerUser(user);
                return ResponseEntity.status(HttpStatus.CREATED).build();
            }
        }

        else if(Objects.equals(user.role().toString(), "USER")){
            userService.registerUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }

        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    @DeleteMapping("{username}")
    public ResponseEntity<Void> delete(@AuthenticationPrincipal UserDetails userDetails,
                                       @PathVariable("username") String username){
        String role = userDetails.getAuthorities().stream().findFirst().map(GrantedAuthority::getAuthority).orElse(null);
        if(Objects.equals(role, "USER") && !Objects.equals(userDetails.getUsername(), username)){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        userService.deleteUser(username);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("{username}/password")
    public ResponseEntity<Void> changePassword(@AuthenticationPrincipal UserDetails userDetails,
                                               @PathVariable("username") String username, @RequestBody String newPassword){
        String role = userDetails.getAuthorities().stream().findFirst().map(GrantedAuthority::getAuthority).orElse(null);
        if(Objects.equals(role, "USER") && !Objects.equals(userDetails.getUsername(), username)){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        userService.changePassword(username, newPassword);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
