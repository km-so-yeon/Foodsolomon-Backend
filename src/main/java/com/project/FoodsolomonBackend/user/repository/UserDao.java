package com.project.FoodsolomonBackend.user.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Mapper
@Repository
public interface UserDao {

    @Select("select count(*) cnt, status from MEMBER where id = #{memberId}")
    public HashMap<String, String> checkUserExists(Long memberId);



}
