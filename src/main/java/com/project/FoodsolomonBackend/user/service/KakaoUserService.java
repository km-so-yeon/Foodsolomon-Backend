package com.project.FoodsolomonBackend.user.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.project.FoodsolomonBackend.config.exception.BaseException;
import com.project.FoodsolomonBackend.user.dto.KakaoUserInfoDto;

import com.project.FoodsolomonBackend.user.dto.PostLoginRes;
import com.project.FoodsolomonBackend.user.repository.UserDao;
import com.project.FoodsolomonBackend.user.repository.UserRepository;
import com.project.FoodsolomonBackend.user.model.User;

import com.project.FoodsolomonBackend.utils.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import static com.project.FoodsolomonBackend.config.exception.BaseResponseStatus.*;


import java.util.HashMap;
import java.util.UUID;

@Service
public class KakaoUserService {

    private final UserRepository userRepository;

    private final JwtService jwtService;

    private final UserDao userDao;

    @Autowired
    public KakaoUserService(UserRepository userRepository, JwtService jwtService, UserDao userDao) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.userDao = userDao;
    }


    public PostLoginRes kakaoLogin(String code) throws JsonProcessingException, BaseException {
        // 1. "인가 코드"로 "액세스 토큰" 요청
        String accessToken = getAccessToken(code);

        // 2. "액세스 토큰"으로 "카카오 사용자 정보" 가져오기
        KakaoUserInfoDto kakaoUserInfo = getKakaoUserInfo(accessToken);


        // 3. "카카오 사용자 정보"로 필요시 회원가입
        User kakaoUser = registerKakaoUserIfNeeded(kakaoUserInfo);

        // 회원가입 잘되는거 확인

        // 4. 강제 로그인 처리
        PostLoginRes res = forceLogin(kakaoUser);

        return res;
    }

    private String getAccessToken(String code) throws JsonProcessingException {
        // HTTP Header 생성
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        // HTTP Body 생성
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "authorization_code");
        body.add("client_id", "e320deaa188bb89bc7b062340050d0fc");
        body.add("redirect_uri", "http://localhost:8181/user/login/kakao");
        body.add("code", code);

        // HTTP 요청 보내기
        HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest =
                new HttpEntity<>(body, headers);
        RestTemplate rt = new RestTemplate();
        ResponseEntity<String> response = rt.exchange(
                "https://kauth.kakao.com/oauth/token",
                HttpMethod.POST,
                kakaoTokenRequest,
                String.class
        );

        // HTTP 응답 (JSON) -> 액세스 토큰 파싱
        String responseBody = response.getBody();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(responseBody);
        return jsonNode.get("access_token").asText();
    }

    private KakaoUserInfoDto getKakaoUserInfo(String accessToken) throws JsonProcessingException {
        // HTTP Header 생성
            // 이것은 카카오 디벨로퍼에서 공식적으로 이렇게 해달라고 한 부분이다.
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessToken);
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        // HTTP 요청 보내기
        HttpEntity<MultiValueMap<String, String>> kakaoUserInfoRequest = new HttpEntity<>(headers);
        RestTemplate rt = new RestTemplate();
        ResponseEntity<String> response = rt.exchange(
                "https://kapi.kakao.com/v2/user/me",
                HttpMethod.POST,
                kakaoUserInfoRequest,
                String.class
        );


        String responseBody = response.getBody();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(responseBody);
        Long id = jsonNode.get("id").asLong();
        String nickname = jsonNode.get("properties")
                .get("nickname").asText();
        String email = jsonNode.get("kakao_account")
                .get("email").asText();


        String ageRange  = jsonNode.get("kakao_account")
                .get("age_range").asText();

        if(ageRange==null)
            ageRange = "";

        System.out.println("카카오 사용자 정보: " + id + ", " + nickname + ", " + email+", " + ageRange);
        return new KakaoUserInfoDto(id, nickname, email, ageRange);
    }

    private User registerKakaoUserIfNeeded(KakaoUserInfoDto kakaoUserInfo) throws BaseException {

        String kakaoEmail = kakaoUserInfo.getEmail();
        User kakaoUser = userRepository.findByEmail(kakaoEmail)
                .orElse(null);


        // 회원가입해야한다면 진행
        if (kakaoUser == null) {
            // 카카오 사용자 이메일과 동일한 이메일을 가진 회원이 있는지 확인

            // 신규 회원가입

            // email: kakao email
            String email = kakaoUserInfo.getEmail();

            // password: random UUID
            String password = UUID.randomUUID().toString();

            // username: kakao nickname
            String nickname = kakaoUserInfo.getNickname();

           // String encodedPassword = passwordEncoder.encode(password);

            String ageRange = "";

            if(kakaoUserInfo.getAgeRange()!=null)
                ageRange = kakaoUserInfo.getAgeRange();

            // role: 일반 사용자
            int role_id = 1;

            String loginmethod = "kakao";


           kakaoUser = new User(email, password, nickname, loginmethod);




            userRepository.save(kakaoUser);
        }
        return kakaoUser;
    }


    // 잘된다.
    private PostLoginRes forceLogin(User kakaoUser) throws BaseException {

        User user = userRepository.findByEmail(kakaoUser.getEmail())
                .orElseThrow(() -> new BaseException(FAILED_TO_LOGIN));


        HashMap<String, String> statusMap = userDao.checkUserExists(user.getId());

        String status = statusMap.get("status");

        PostLoginRes res = new PostLoginRes(user.getId(), "", status);


        try{

            String jwt = jwtService.createJwt(user.getId());
            res.setJwt(jwt);

            return res;

        }catch (Exception e){
            throw new BaseException(FAILED_TO_LOGIN);
        }

    }

}


