package com.project.FoodsolomonBackend.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserInfoDto {

    String nickname;
    boolean isAdmin;
}
