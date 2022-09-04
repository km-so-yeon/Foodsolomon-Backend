package com.project.FoodsolomonBackend.user.repository;


import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.jpa.repository.JpaRepository;

import com.project.FoodsolomonBackend.user.model.User;


public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
    int countUsersByEmail(String email);
    int countUsersByNickname(String nickname);




}