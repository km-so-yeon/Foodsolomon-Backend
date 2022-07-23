package com.project.FoodsolomonBackend.user.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Api("HomeController API V1.0")
public class HomeController {


    @GetMapping("/")
    @ApiOperation(value = "메인화면", notes = "메인화면 이동용")
    public String home() {


        return "index";
    }
}
