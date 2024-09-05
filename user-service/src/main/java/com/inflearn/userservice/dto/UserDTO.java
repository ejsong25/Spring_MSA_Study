package com.inflearn.userservice.dto;

import lombok.Data;

import java.util.Date;
//가공되는 User 정보
@Data
public class UserDTO {
    private String email;
    private String name;
    private String pwd;
    private String userId;
    private Date createdAt;

    private String encryptedPwd;
}
