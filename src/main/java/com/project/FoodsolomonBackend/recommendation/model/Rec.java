package com.project.FoodsolomonBackend.recommendation.model;


import com.project.FoodsolomonBackend.common.model.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter // get 함수를 일괄적으로 만들어줍니다.
@NoArgsConstructor // 기본 생성자를 만들어줍니다.
@Entity // DB 테이블 역할을 합니다.
public class Rec extends BaseEntity {
    // ID가 자동으로 생성 및 증가합니다.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;


    @Column(nullable = false, unique = true)
    private String foodName;


    @Column(nullable = false)
    private String path;

    @Column(nullable = false)
    private String fileName;

    @Column(nullable = false)
    private String modifierId;

    public Rec(String foodName, String path, String fileName, String modifierId) {
        this.foodName = foodName;
        this.path = path;
        this.fileName = fileName;
        this.modifierId = modifierId;
        super.setStatus("active");
    }
}