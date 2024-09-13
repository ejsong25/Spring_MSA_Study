package com.inflearn.userservice.controller;

import com.inflearn.userservice.dto.UserDTO;
import com.inflearn.userservice.jpa.UserEntity;
import com.inflearn.userservice.service.UserService;
import com.inflearn.userservice.vo.Greeting;
import com.inflearn.userservice.vo.RequestUser;
import com.inflearn.userservice.vo.ResponseUser;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user-service")
public class UserController {
    private UserService userService;

    // 1. Environment 객체 사용
    private Environment env;

    @Autowired
    public UserController(Environment env, UserService userService) {
        this.env = env;
        this.userService = userService;
    }
    // 2. value annotation
    @Autowired
    private Greeting greeting;

    @GetMapping("/health_check")
    public String status(){
        return String.format("It's working in User Service on PORT %s",
                env.getProperty("local.server.port"));
    }
    
    @GetMapping("/welcome")
    public String welcome(){
//        return env.getProperty("greeting.message");
        return greeting.getMessage();
    }

    //회원가입
    @PostMapping("/users")
    public ResponseEntity<ResponseUser> createUser(@RequestBody RequestUser user){
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        UserDTO userDto = mapper.map(user, UserDTO.class);
        userService.createUser(userDto);

        //반환코드 201번 성공코드 + 메세지
        ResponseUser responseUser = mapper.map(userDto, ResponseUser.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseUser);
    }

    //전체 회원 조회
    @GetMapping("/users")
    public ResponseEntity<List<ResponseUser>> getUsers(){
        Iterable<UserEntity> userList = userService.getUserByAll();

        List<ResponseUser> result = new ArrayList<>();
        userList.forEach(v -> {
            result.add(new ModelMapper().map(v, ResponseUser.class));
        });

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    //
    @GetMapping("/users/{userId}")
    public ResponseEntity<ResponseUser> getUser(@PathVariable("userId") String userId){
        UserDTO userDTO = userService.getUserByUserId(userId);

        ResponseUser returnValue = new ModelMapper().map(userDTO, ResponseUser.class);

        return ResponseEntity.status(HttpStatus.OK).body(returnValue);
    }
}
