package com.nuptsast.data;

import com.nuptsast.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Zheng on 16/7/21.
 * All Rights Reversed.
 */
public interface UserRepository extends JpaRepository<User, Long> {
}
