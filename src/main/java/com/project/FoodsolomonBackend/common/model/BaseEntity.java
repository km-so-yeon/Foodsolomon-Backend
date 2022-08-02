package com.project.FoodsolomonBackend.common.model;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.sql.Timestamp;

@Setter
@Getter
@MappedSuperclass
@EntityListeners({AuditingEntityListener.class})
@DynamicUpdate
public class BaseEntity {

    // 상태
    @Column(nullable = false, columnDefinition = "varchar(10) default 'active'")
    private String status;

    // 생성일자
    @Column(updatable = false, columnDefinition = "TIMESTAMP")
    @CreatedDate
    private Timestamp createdTime;

    // 수정일자
    @Column(columnDefinition = "TIMESTAMP")
    @LastModifiedDate
    private Timestamp updatedTime;




}
