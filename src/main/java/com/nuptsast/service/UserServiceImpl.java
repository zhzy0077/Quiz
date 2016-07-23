package com.nuptsast.service;

import com.nuptsast.data.UserRepository;
import com.nuptsast.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Created by Zheng on 16/7/22.
 * All Rights Reversed.
 */
@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User register(User user) {
        String encodedPass = new BCryptPasswordEncoder().encode(user.getPassword());
        User newUser = new User(user.getUsername(), encodedPass);
        return userRepository.save(newUser);
    }
}
