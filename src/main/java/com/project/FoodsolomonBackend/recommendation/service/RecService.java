package com.project.FoodsolomonBackend.recommendation.service;


import com.project.FoodsolomonBackend.recommendation.dto.GetRecListRes;
import com.project.FoodsolomonBackend.recommendation.repository.SelectionRecDao;
import com.project.FoodsolomonBackend.recommendation.repository.SelectionRecRepository;
import com.project.FoodsolomonBackend.utils.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;


@Service
@Transactional(rollbackOn = Exception.class)
public class RecService {


    private final SelectionRecRepository selectRepository;

    private final JwtService jwtService;

    private final SelectionRecDao selectDao;

    @Autowired
    public RecService(SelectionRecRepository selectRepository, JwtService jwtService, SelectionRecDao selectDao) {
        this.selectRepository = selectRepository;
        this.jwtService = jwtService;
        this.selectDao = selectDao;
    }

    public List<GetRecListRes> retrieveRecList(){

        List<GetRecListRes> foodList = selectDao.selectRecList();

        List<GetRecListRes> randomResult = new ArrayList<>();

        int index = 0;


        for(int i=0;i<4;i++){

            index = (int)(Math.floor(Math.random()*foodList.size()));
            randomResult.add(foodList.get(index));
            foodList.remove(index);

        }


        return randomResult;

    }




}


