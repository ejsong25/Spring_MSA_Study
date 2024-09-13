package com.inflearn.userservice.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL) //null이 아닌 값만 저장
public class ResponseUser {
    private String email;
    private String name;
    private String userId;
    
    private List<ResponseOrder> orders;
}
