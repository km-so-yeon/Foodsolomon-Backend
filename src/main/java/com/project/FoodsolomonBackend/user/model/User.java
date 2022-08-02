package com.project.FoodsolomonBackend.user.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.project.FoodsolomonBackend.common.model.BaseEntity;
import com.project.FoodsolomonBackend.config.exception.BaseException;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static com.project.FoodsolomonBackend.user.validator.UserValidator.validatedUserInput;

@Setter
@Getter // get 함수를 일괄적으로 만들어줍니다.
@NoArgsConstructor // 기본 생성자를 만들어줍니다.
@Entity(name = "MEMBER") // DB 테이블 역할을 합니다.
public class User extends BaseEntity {
    // ID가 자동으로 생성 및 증가합니다.
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    // nullable: null 허용 여부
    // unique: 중복 허용 여부 (false 일때 중복 허용)
    @Column(nullable = false, unique = true)
    private String email;
    

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String nickname;
    
    @Column(name = "age_range")
    private String ageRange;

    @Column(name = "login_method")
    private String loginMethod;


    // enum이기 떄문에 user 혹은 admin만 들어올 수 있다.
    	// db에 저장될 때는 이 enum값으로 저장되는 것이 아니라  String으로 저장하라고 한다.
    @Column(nullable = false, name = "role_id")
    private int roleId;


	public User(String email, String password, String nickname, String ageRange, int roleId) throws BaseException {

        validatedUserInput(email, password, nickname, ageRange);

		this.email = email;
		this.password = password;
		this.nickname = nickname;
		this.ageRange = ageRange;
		this.roleId = roleId;
        this.setStatus("active");
	}

    public User(String email, String password, String nickname, String ageRange, int roleId, String loginMethod) throws BaseException {

        validatedUserInput(email, password, nickname, ageRange);

        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.ageRange = ageRange;
        this.roleId = roleId;
        this.loginMethod = loginMethod;
        this.setStatus("active");
    }
    
 

}