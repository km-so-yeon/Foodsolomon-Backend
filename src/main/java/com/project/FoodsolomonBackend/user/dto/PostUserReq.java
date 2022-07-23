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

    @ApiModelProperty(value = "연령대(1~9, 10~14, 15~19, 20~29, ..., 80~89, 90~ 중에서 선택)",  example = "10~14")
    private String ageRange;

    @ApiModelProperty(value = "관리자인지 여부(기본값: false)", example = "true 아니면 false")
    private boolean admin = false;

    @ApiModelProperty(value = "관리자 토큰(admin이 true인 경우에만)", example = "fsdfsdfds!ds")
    private String adminToken = "";


}