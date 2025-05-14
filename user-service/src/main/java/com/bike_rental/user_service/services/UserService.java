package com.bike_rental.user_service.services;

import com.bike_rental.user_service.dto.UserRequest;
import com.bike_rental.user_service.entities.User;
import com.bike_rental.user_service.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.webauthn.management.UserCredentialRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserService {

    final private UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void registerUser(UserRequest userRequest){
        if(userRepository.existsByUsername(userRequest.username())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This username is already used");
        }
        else if(userRepository.existsByEmail(userRequest.email())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This email is already used");
        }

        User user = new User("USER", userRequest.username(), userRequest.email(),
                passwordEncoder.encode(userRequest.password()));

        userRepository.save(user);
    }



}
