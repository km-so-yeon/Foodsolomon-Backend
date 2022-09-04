package com.project.FoodsolomonBackend.user.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.project.FoodsolomonBackend.config.exception.BaseException;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static com.project.FoodsolomonBackend.user.validator.UserValidator.*;

@Setter
@Getter // get 함수를 일괄적으로 만들어줍니다.
@NoArgsConstructor // 기본 생성자를 만들어줍니다.
@Entity(name = "MEMBER") // DB 테이블 역할을 합니다.
public class User {
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

    @Column(name = "login_method")
    private String loginMethod;




	public User(String email, String password, String nickname) throws BaseException {

        validatedUserInput(email,nickname);

		this.email = email;
		this.password = password;
		this.nickname = nickname;

	}

    public User(String email, String password, String nickname,  String loginMethod) throws BaseException {

        validatedUserInput(email, nickname);

        this.email = email;
        this.password = password;
        this.nickname = nickname;

        this.loginMethod = loginMethod;
    }
    
 

}