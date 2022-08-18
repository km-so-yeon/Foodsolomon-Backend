package com.project.FoodsolomonBackend.recommendation.controller;


import com.project.FoodsolomonBackend.config.exception.BaseResponse;
import com.project.FoodsolomonBackend.recommendation.dto.GetRecListRes;
import com.project.FoodsolomonBackend.recommendation.service.RecService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Controller
@Api(value = "RecTestController API 테스트용")
public class RecTestController {


    private final RecService recService;

    @Autowired
    public RecTestController(RecService recService) {
        this.recService = recService;
    }





    @GetMapping("/rec/test")
    public String retrieveRecList(){

        return "recommendation";
    }


}