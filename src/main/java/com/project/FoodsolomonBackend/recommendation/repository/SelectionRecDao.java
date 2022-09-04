package com.project.FoodsolomonBackend.recommendation.repository;

import com.project.FoodsolomonBackend.recommendation.dto.GetRecListRes;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface SelectionRecDao {

    @Select("select food_name name, file_name url from REC")
    public List<GetRecListRes> selectRecList();


}
