package com.project.FoodsolomonBackend.user.service;


import java.util.HashMap;
import java.util.Optional;

import javax.transaction.Transactional;

import com.project.FoodsolomonBackend.user.dto.PostLoginReq;
import com.project.FoodsolomonBackend.user.dto.PostLoginRes;
import com.project.FoodsolomonBackend.user.repository.UserDao;
import com.project.FoodsolomonBackend.utils.JwtService;
import com.project.FoodsolomonBackend.utils.SHA256;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import com.project.FoodsolomonBackend.config.exception.BaseException;
import com.project.FoodsolomonBackend.user.dto.PostUserReq;
import com.project.FoodsolomonBackend.user.model.User;
import com.project.FoodsolomonBackend.user.repository.UserRepository;

import static com.project.FoodsolomonBackend.config.exception.BaseResponseStatus.*;
import static com.project.FoodsolomonBackend.utils.FormalValidationException.checkPwdFormal;


@Service
@Transactional(rollbackOn = Exception.class)
public class UserService {


    private final UserRepository userRepository;

    private final JwtService jwtService;

    private final UserDao userDao;

    @Autowired
    public UserService(UserRepository userRepository, JwtService jwtService, UserDao userDao) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.userDao = userDao;
    }

    public int registerUser(PostUserReq requestDto) throws BaseException{
    	
    	
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



        if(requestDto.getPassword().length()>15 || requestDto.getPassword().length()<8){
            throw new BaseException(POST_MEMBERS_PASSWORD_LENGTH);
        }

// 패스워드 암호화
        String password = new SHA256().encrypt(requestDto.getPassword());
        String nickname = requestDto.getNickname();

        


        User user = new User(email, password, nickname);

        user.setLoginMethod("original");

        user = userRepository.save(user);
        
        int result = 0;
        
        if(user!=null) 
        	result = 1;
        
        if(result !=1) 
        	throw new BaseException(FAILED_TO_REGISTER);
        
        
        return result;
        
    }

    // 로그인을 했을 때, 로그인 정보가 있는지. 유저 상태 확인.
    public PostLoginRes login(PostLoginReq req) throws BaseException{


        // 유저가 존재한 상황에서 비밀번호를 얻는다.
        User user = userRepository.findByEmail(req.getEmail())
                .orElseThrow(() -> new BaseException(INVALID_MEMBER));
        
        // 상태 확인
        HashMap<String, String> statusMap = userDao.checkUserExists(user.getId());

        String status = statusMap.get("status");

        PostLoginRes result = new PostLoginRes(user.getId(), null, status);
        

        String encryptPwd;

        try {
            // 입력 비밀번호 암호화
            encryptPwd = new SHA256().encrypt(req.getPassword());
        } catch (Exception ignored) { // exception을 무시할때 ignored로 표기한다.

            throw new BaseException(PASSWORD_DECRYPTION_ERROR);
        }

        // 유효성 검사 3. 비밀번호 확인
        if(user.getPassword().equals(encryptPwd)){

            String jwt = jwtService.createJwt(user.getId());
            result.setJwt(jwt);

            return result;
        }
        else{

            throw new BaseException(FAILED_TO_LOGIN);
        }

    }


    public boolean checkEmailDuplicated(String email) {

        boolean result = (userRepository.countUsersByEmail(email) != 0);

        return result;
    }

    public boolean checkNicknameDuplicated(String nickname) {

        boolean result = (userRepository.countUsersByNickname(nickname) != 0);

        return result;
    }
}


