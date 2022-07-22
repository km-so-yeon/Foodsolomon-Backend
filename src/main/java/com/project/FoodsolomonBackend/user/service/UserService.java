package com.project.FoodsolomonBackend.user.service;


import static com.project.FoodsolomonBackend.config.exception.BaseResponseStatus.FAILED_TO_REGISTER;
import static com.project.FoodsolomonBackend.config.exception.BaseResponseStatus.POST_MEMBERS_DUPLICATED_EMAIL;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.FoodsolomonBackend.config.exception.BaseException;
import com.project.FoodsolomonBackend.user.dto.SignupRequestDto;
import com.project.FoodsolomonBackend.user.model.User;
import com.project.FoodsolomonBackend.user.repository.UserRepository;


@Service
@Transactional(rollbackOn = Exception.class)
public class UserService {

    private final PasswordEncoder passwordEncoder;
    
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    private static final String ADMIN_TOKEN = "fsdfsdfds!ds";



    public int registerUser(SignupRequestDto requestDto) throws BaseException{
    	
    	
    	 String email = requestDto.getEmail();
 
    	try {
    	
       

// 회원 ID 중복 확인
        // isPresent는 optional 클래스의 함수이다. 
        Optional<User> found = userRepository.findByEmail(email);
        if (found.isPresent()) {
            throw new BaseException(POST_MEMBERS_DUPLICATED_EMAIL);
        }
    	}catch(Exception e) {
    		throw new BaseException(POST_MEMBERS_DUPLICATED_EMAIL);
    	}


// 패스워드 암호화
        String password = passwordEncoder.encode(requestDto.getPassword());
        String nickname = requestDto.getNickname();
        String birthday = requestDto.getBirthday();
        
        
// 사용자 ROLE 확인
        int role_id = 1;
        
        // isAdmin은 뭐냐? admin 이라는 boolean멤버변수가 있는데, true면 관리자 false면 user가 되야하기에
            // 일단은 처음에 user로 세팅하고 dto에서 유저가 아니라면(admin=true)면 토큰 비교, 아니면 그대로 유저로 ㄱㄱ
        if (requestDto.isAdmin()) {
            if (!requestDto.getAdminToken().equals(ADMIN_TOKEN)) {
                throw new IllegalArgumentException("관리자 암호가 틀려 등록이 불가능합니다.");
            }
            role_id = 2;
        }

        User user = new User(email, password, nickname, birthday, role_id);
        user = userRepository.save(user);
        
        int result = 0;
        
        if(user!=null) 
        	result = 1;
        
        if(result !=1) 
        	throw new BaseException(FAILED_TO_REGISTER);
        
        
        return result;
        
    }


    
}


