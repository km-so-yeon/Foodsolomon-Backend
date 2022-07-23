package com.project.FoodsolomonBackend.user.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("로그인 요청 데이터 객체")
public class PostLoginReq {

    @ApiModelProperty(value = "이메일", example = "abc@naver.com", required = true)
    private String email;

    @ApiModelProperty(value = "패스워드", example = "123", required = true)
    private String password;

}
