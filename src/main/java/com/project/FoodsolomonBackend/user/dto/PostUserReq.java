package com.project.FoodsolomonBackend.user.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@ApiModel("회원가입 요청 데이터 객체")
public class PostUserReq {

    @ApiModelProperty(value = "이메일", example = "abc@naver.com", required = true)
	private String email;
    
    @ApiModelProperty(value = "패스워드", example = "qefsd12!", required = true)
    private String password;

    @ApiModelProperty(value = "닉네임", example = "도지", required = true)
    private String nickname;




}