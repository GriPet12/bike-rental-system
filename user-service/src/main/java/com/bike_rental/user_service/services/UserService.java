package com.bike_rental.user_service.services;

import com.bike_rental.user_service.dto.Role;
import com.bike_rental.user_service.dto.UserDTO;
import com.bike_rental.user_service.dto.UserRequest;
import com.bike_rental.user_service.entities.User;
import com.bike_rental.user_service.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserDTO toDTO(User u){
        return new UserDTO(u.getId(), u.getUsername(), u.getEmail(),  Role.valueOf(u.getRole()));
    }

    public UserDTO getUser(String username){
        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Wrong username"));
        return toDTO(user);
    }

    public void registerUser(UserRequest userRequest){
        if(userRepository.existsByUsername(userRequest.username())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This username is already used");
        }
        if(userRepository.existsByEmail(userRequest.email())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This email is already used");
        }

        User user = new User(userRequest.role().toString(), userRequest.username(), userRequest.email(),
                passwordEncoder.encode(userRequest.password()));

        userRepository.save(user);
    }

    public UserDTO getUserByEmail(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Wrong email"));
        return toDTO(user);
    }

    public UserDTO getUser(int id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return toDTO(user);
    }

    public void deleteUser(int id){
        if(userRepository.existsById(id)){
            userRepository.deleteById(id);
        }
        else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }


    public void changePassword(int userId, String newPassword) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Wrong ID"));

        String hashed_password = passwordEncoder.encode(newPassword);
        user.setPassword(hashed_password);
        userRepository.save(user);
    }

}

