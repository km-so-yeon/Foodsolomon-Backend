package com.project.FoodsolomonBackend.recommendation.controller;


import com.project.FoodsolomonBackend.config.exception.BaseResponse;
import com.project.FoodsolomonBackend.recommendation.dto.GetRecListRes;
import com.project.FoodsolomonBackend.recommendation.service.RecService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@Api(value = "RecController API V1.0")
@RequestMapping("/rec")
public class RecController {


    private final RecService recService;

    @Autowired
    public RecController(RecService recService) {
        this.recService = recService;
    }





    @GetMapping("")
    @ApiOperation(value = "랜덤 추천", notes = "axios 방식, JSON request(application/json)로 진행해주세요. 일단은 4개만 추천받을 수 있도록 해놨습니다.")
    public BaseResponse<List<GetRecListRes>> retrieveRecList(){

        List<GetRecListRes> result = recService.retrieveRecList();


        return new BaseResponse<>(result);
    }


}