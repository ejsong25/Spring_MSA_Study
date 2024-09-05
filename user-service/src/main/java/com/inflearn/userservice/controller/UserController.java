package com.inflearn.userservice.controller;

import com.inflearn.userservice.dto.UserDTO;
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

@RestController
@RequestMapping("/")
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
        return "It's working in User Service";
    }
    @GetMapping("/welcome")
    public String welcome(){
//        return env.getProperty("greeting.message");
        return greeting.getMessage();
    }

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
}
