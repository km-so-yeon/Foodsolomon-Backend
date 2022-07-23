package com.project.FoodsolomonBackend.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostLoginRes {

    private Long userId;
    private String jwt;
    private boolean admin;
    private String status;

}
