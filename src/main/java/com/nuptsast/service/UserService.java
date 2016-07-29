package com.nuptsast.service;

import com.nuptsast.model.User;

/**
 * Created by Zheng on 16/7/21.
 * All Rights Reversed.
 */

public interface UserService {
    User register(User user);

    User getUser(String username);

    User updateUser(User user);
}
