package com.project.FoodsolomonBackend.user.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class PostLoginRes {

    @ApiModelProperty(value = "회원고유번호", example = "10", required = true)
    private Long userId;
    
    @ApiModelProperty(value = "jwt 토큰(클라이언트에 저장)", example = "dadadabv1", required = true)
    private String jwt;

    @ApiModelProperty(value = "관리자 여부", example = "false", required = true)
    private boolean admin;

    @ApiModelProperty(value = "회원상태", example = "active", required = true)
    private String status;

}
