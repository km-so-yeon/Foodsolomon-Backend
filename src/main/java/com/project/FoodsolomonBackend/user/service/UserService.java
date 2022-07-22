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


    private static final String ADMIN_TOKEN = "fsdfsdfds!ds";



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


// 패스워드 암호화
        String password = new SHA256().encrypt(requestDto.getPassword());
        String nickname = requestDto.getNickname();
        String ageRange = requestDto.getAgeRange();
        
        
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

        User user = new User(email, password, nickname, ageRange, role_id);
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

        boolean admin= (user.getRoleId() == 2);
        
        // 상태 확인
        HashMap<String, String> statusMap = userDao.checkUserExists(user.getId());

        String status = statusMap.get("status");

        PostLoginRes result = new PostLoginRes(user.getId(), null, admin, status);
        

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


    
}


