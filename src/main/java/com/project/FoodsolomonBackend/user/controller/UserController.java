package com.project.FoodsolomonBackend.user.controller;

import com.fasterxml.jackson.core.JsonProcessingException;

import com.project.FoodsolomonBackend.user.dto.PostLoginReq;
import com.project.FoodsolomonBackend.user.dto.PostLoginRes;
import com.project.FoodsolomonBackend.user.model.User;
import com.project.FoodsolomonBackend.user.service.KakaoUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.project.FoodsolomonBackend.config.exception.BaseException;
import com.project.FoodsolomonBackend.config.exception.BaseResponse;
import com.project.FoodsolomonBackend.user.dto.PostUserReq;
import com.project.FoodsolomonBackend.user.service.UserService;


@RestController
@Api(value = "UserController API V1.0")
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final KakaoUserService kaKaoUserService;

    @Autowired
    public UserController(UserService userService, KakaoUserService kaKaoUserService) {
        this.userService = userService;
        this.kaKaoUserService = kaKaoUserService;
    }
    

    @PostMapping("/signup")
    @ApiOperation(value = "회원가입", notes = "axios 방식, JSON request(application/json)로 로그인을 진행해주세요")
    public BaseResponse<Integer> registerUser(@ApiParam(value= "회원가입 요청 객체", name = "PostUserReq") @RequestBody PostUserReq requestDto) {

    	int result = 0;
    	
    	try {
    		result = userService.registerUser(requestDto);
    		
    	}catch (BaseException exception){

            return new BaseResponse<>(exception.getStatus());
        }

        return new BaseResponse<Integer>(result);

    }

    @PostMapping("/login")
    @ApiOperation(value = "일반 로그인", notes = "axios 방식, JSON request(application/json)로 로그인을 진행해주세요")
    public BaseResponse<PostLoginRes> login(@ApiParam(value= "로그인 요청 객체", name = "PostLoginReq") @RequestBody PostLoginReq req) {
    
        try {

            PostLoginRes result = userService.login(req);

            return new BaseResponse<>(result);

        }catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }

    }


    @GetMapping("/login/kakao")
    @ApiOperation(value = "카카오 로그인", notes = "진행 시, https://kauth.kakao.com/oauth/authorize?client_id=e320deaa188bb89bc7b062340050d0fc&redirect_uri=http://localhost:8181/user/login/kakao&response_type=code 링크 호출")
    @ApiImplicitParam(name = "code", value = "카카오 로그인 시, 발급받는 인가 코드", required = true, dataType = "String")
    public BaseResponse<PostLoginRes> kakaoLogin(@RequestParam String code) throws JsonProcessingException {
        // authorizedCode: 카카오 서버로부터 받은 인가 코드
        // questString으로 넘김받은

        PostLoginRes result = null;

        try {
            // 서비스 처리
            result =  kaKaoUserService.kakaoLogin(code);
        }catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
        // 로그인까지는 잘 되는데, 다시 user/login 으로 가는 느낌?
            // view로 하면 또 잘되고
        return new BaseResponse<>(result);
    }
    

}