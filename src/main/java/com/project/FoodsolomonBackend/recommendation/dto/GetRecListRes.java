package com.project.FoodsolomonBackend.recommendation.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetRecListRes {

    @ApiModelProperty(value = "음식명", example = "고기", required = true)
    String name;

    @ApiModelProperty(value = "이미지경로(프로젝트 기준)", example = "/image/recommendation/고기.jpeg", required = true)
    String url;

}
