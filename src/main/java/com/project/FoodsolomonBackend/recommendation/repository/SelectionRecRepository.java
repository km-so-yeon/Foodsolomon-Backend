package com.project.FoodsolomonBackend.recommendation.repository;


import com.project.FoodsolomonBackend.recommendation.model.Rec;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface SelectionRecRepository extends JpaRepository<Rec, Long> {

    Optional<Rec> findByFoodName(String Name);
    List<Rec> findAll();




}