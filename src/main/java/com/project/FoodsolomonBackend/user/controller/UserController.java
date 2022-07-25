package com.project.FoodsolomonBackend.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.project.FoodsolomonBackend.config.exception.BaseException;
import com.project.FoodsolomonBackend.config.exception.BaseResponse;
import com.project.FoodsolomonBackend.user.dto.SignupRequestDto;
import com.project.FoodsolomonBackend.user.service.UserService;


@Controller
public class UserController {

    private final UserService userService;
 
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
       
    }

    // 회원 로그인 페이지
    @GetMapping("/user/loginView")
    public String login() {
        return "login";
    }

    // 회원 가입 페이지
    @GetMapping("/user/signup")
    public String signup() {
        return "signup";
    }

    // 회원 가입 요청 처리
    @PostMapping("/user/signup")
    public BaseResponse<Integer> registerUser(@RequestBody SignupRequestDto requestDto) {
        
    	
    	
    	
    	
    	
    	int result = 0;
    	
    	try {
    		result = userService.registerUser(requestDto);
    		
    	}catch (BaseException exception){

            return new BaseResponse<>(exception.getStatus());
        }
        
        return new BaseResponse<Integer>(result);
        
        
    }
    

}