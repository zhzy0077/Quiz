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
        User newUser = new User(user.getUsername(), encodedPass, user.getTargetDepartment(), user.getPhoneNumber());
        return userRepository.save(newUser);
    }

    @Override
    public User getUser(String username) {
        return userRepository.findOneByUsername(username);
    }

    @Override
    public void addAnswer(Long userId, Long questionId, String answer) {
        User user = userRepository.findOne(userId);
        user.getAnswer().getAnswer().put(questionId, answer);
    }
}
