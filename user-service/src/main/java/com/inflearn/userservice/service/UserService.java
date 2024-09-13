package com.inflearn.userservice.service;

import com.inflearn.userservice.dto.UserDTO;
import com.inflearn.userservice.jpa.UserEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    // 구현할 함수 정의
    UserDTO createUser(UserDTO userDTO);
    UserDTO getUserByUserId(String userId);
    Iterable<UserEntity> getUserByAll();

    UserDTO getUserDetailsByEmail(String userName);
}
