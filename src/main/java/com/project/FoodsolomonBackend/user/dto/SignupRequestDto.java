package com.project.FoodsolomonBackend.user.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SignupRequestDto {
    
	private String email;
    private String password;
    
    private String nickname;
    private String birthday;
    
    private boolean admin = false;
    private String adminToken = "";
}