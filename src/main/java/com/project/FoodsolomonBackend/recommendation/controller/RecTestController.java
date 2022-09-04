package com.project.FoodsolomonBackend.recommendation.controller;


import com.project.FoodsolomonBackend.recommendation.service.RecService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


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