package com.bike_rental.user_service.services;


import org.springframework.stereotype.Service;

@Service
public class UserService {

//    //final private UserRepository userRepository;
//    private final PasswordEncoder passwordEncoder;
//
//    @Autowired
//    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
//      //  this.userRepository = userRepository;
//        this.passwordEncoder = passwordEncoder;
//    }
//
//    public void registerUser(UserRequest userRequest){
//        if(userRepository.existsByUsername(userRequest.username())){
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This username is already used");
//        }
//        else if(userRepository.existsByEmail(userRequest.email())){
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This email is already used");
//        }
//
//        User user = new User("USER", userRequest.username(), userRequest.email(),
//                passwordEncoder.encode(userRequest.password()));
//
//        userRepository.save(user);
//    }



}
