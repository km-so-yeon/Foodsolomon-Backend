package com.project.FoodsolomonBackend.user.controller;

import com.fasterxml.jackson.core.JsonProcessingException;

import com.project.FoodsolomonBackend.user.dto.PostLoginReq;
import com.project.FoodsolomonBackend.user.dto.PostLoginRes;
import com.project.FoodsolomonBackend.user.model.User;
import com.project.FoodsolomonBackend.user.service.KakaoUserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.project.FoodsolomonBackend.config.exception.BaseException;
import com.project.FoodsolomonBackend.config.exception.BaseResponse;
import com.project.FoodsolomonBackend.user.dto.PostUserReq;
import com.project.FoodsolomonBackend.user.service.UserService;


@RestController
public class UserController {

    private final UserService userService;
    private final KakaoUserService kaKaoUserService;

    @Autowired
    public UserController(UserService userService, KakaoUserService kaKaoUserService) {
        this.userService = userService;
        this.kaKaoUserService = kaKaoUserService;
    }




    @GetMapping("/")
    public String home(Model model, User user) {

        if (user != null) {
            model.addAttribute("nickname", user.getNickname());

            model.addAttribute("isAdmin", false);

            // admin인 경우에만
            if (user.getRoleId() == 2) {
                model.addAttribute("isAdmin", true);
            }
        }

        return "index";
    }

    // 회원 가입 요청 처리
    @PostMapping("/user/signup")
    @ResponseBody
    public BaseResponse<Integer> registerUser(@RequestBody PostUserReq requestDto) {

    	int result = 0;
    	
    	try {
    		result = userService.registerUser(requestDto);
    		
    	}catch (BaseException exception){

            return new BaseResponse<>(exception.getStatus());
        }

        return new BaseResponse<Integer>(result);

    }

    @PostMapping("/user/login")
    public BaseResponse<PostLoginRes> login(@RequestBody PostLoginReq req) {

        try {

            PostLoginRes result = userService.login(req);

            return new BaseResponse<>(result);

        }catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }

    }


    @GetMapping("/user/kakao")
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