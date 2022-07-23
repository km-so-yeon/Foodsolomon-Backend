package com.project.FoodsolomonBackend.utils;


import com.project.FoodsolomonBackend.config.exception.BaseException;
import com.project.FoodsolomonBackend.utils.secret.Secret;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

import static com.project.FoodsolomonBackend.config.exception.BaseResponseStatus.*;

@Service
public class JwtService {


       // JWT 생성
        public String createJwt(Long memberId){
            Date now = new Date();
            return Jwts.builder()
                    .setHeaderParam("type","jwt")
                    .claim("memberId",memberId)
                    .setIssuedAt(now)
                    .setExpiration(new Date(System.currentTimeMillis()+1*(1000*60*60*24*30))) // 30일치
                    .signWith(SignatureAlgorithm.HS256, Secret.JWT_SECRET_KEY)
                    .compact();
        }

        //Header에서 X-ACCESS-TOKEN 으로 JWT 추출
        public String getJwt(){
            HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
            return request.getHeader("X-ACCESS-TOKEN");
        }

        // JWT 추출
        public Long getMemberIdx() throws BaseException {
          
            String accessToken = getJwt();
            if(accessToken == null || accessToken.length() == 0){
                throw new BaseException(EMPTY_JWT);
            }

            Jws<Claims> claims;
            try{
                claims = Jwts.parser()
                        .setSigningKey(Secret.JWT_SECRET_KEY)
                        .parseClaimsJws(accessToken);
            } catch (Exception ignored) {
                throw new BaseException(INVALID_JWT);
            }

            Long memberId = claims.getBody().get("memberId",Long.class);

            return memberId;
        }


}
