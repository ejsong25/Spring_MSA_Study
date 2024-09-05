package com.inflearn.userservice.vo;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

//User가 입력하는 정보
@Data
public class RequestUser {
    @NotNull(message = "Email cannot be null")
    @Size(min=2, message = "Email not be less than two characters")
    @Email
    private String email;

    @NotNull(message = "Name cannot be null")
    @Size(min=2, message = "Name not be less than two characters")
    private String name;

    @NotNull(message = "Password cannot be null")
    @Size(min=8, message = "Password must be greater than eight characters")
    private String pwd;
}
