package com.portfolioehadad.portfolio.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class userService {
    @Autowired
    public UserRepository userRepository;
    public User createUser(User user ){
        return userRepository.save(user);
    }
    public Profile createProfile(Profile profile){
        return userRepository.save(profile);
    }
}
