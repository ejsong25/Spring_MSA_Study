package com.inflearn.userservice.dto;

import com.inflearn.userservice.vo.ResponseOrder;
import lombok.Data;

import java.util.Date;
import java.util.List;

//가공되는 User 정보
@Data
public class UserDTO {
    private String email;
    private String name;
    private String pwd;
    private String userId;
    private Date createdAt;

    private String encryptedPwd;

    private List<ResponseOrder> orders;
}
