package com.project.FoodsolomonBackend.user.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PostUserReq {
    
	private String email;
    private String password;
    
    private String nickname;
    private String ageRange;
    
    private boolean admin = false;
    private String adminToken = "";


}