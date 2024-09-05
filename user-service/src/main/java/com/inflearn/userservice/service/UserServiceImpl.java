package com.inflearn.userservice.service;

import com.inflearn.userservice.dto.UserDTO;
import com.inflearn.userservice.jpa.UserEntity;
import com.inflearn.userservice.jpa.UserRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MatchingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        userDTO.setUserId(UUID.randomUUID().toString());

        //userDTO -> userEntity
        ModelMapper mapper = new ModelMapper();
//      //modelMapper 환경 설정 - 완벽히 일치하는 경우 변환 가능
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        UserEntity userEntity = mapper.map(userDTO, UserEntity.class);

        // notNull이기 때문에 기본 default값 설정
        userEntity.setEncryptedPassword("encrypted_password");

        userRepository.save(userEntity);

        UserDTO returnUserDTO = mapper.map(userEntity, UserDTO.class);
        return returnUserDTO;
    }
}
