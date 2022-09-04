package com.project.FoodsolomonBackend.utils;


import com.project.FoodsolomonBackend.config.exception.BaseException;
import com.project.FoodsolomonBackend.user.model.User;

import static com.project.FoodsolomonBackend.config.exception.BaseResponseStatus.*;

public class LogicalValidationException {

    // 유효하지않음
    
        // 유효하지않은 jwt
        public static void checkJwtVaildated(String jwt) throws BaseException {

            throw new BaseException(INVALID_JWT);
        }


        // 권한이 없는 유저
        public static  void checkUserAuth(User user) throws BaseException{

            throw new BaseException(INVALID_MEMBER_JWT);
        }


        // 동네설정 다시






         // 유효하지않은 회원
        public static  void checkMemberExists(int result) throws BaseException{

            if(result != 1){
                throw new BaseException(INVALID_MEMBER);
            }

        }

        // 회원상태 정보 확인
        public static  void checkMemberStatus(String status) throws BaseException{

            if(status.equals("blocked") || status.equals("deleted")){
                throw new BaseException(BLOCKED_MEMBER);
            }


        }







//    // 유효하지앟은 게사물
//        public static  void checkPostExists(int result) throws BaseException{
//
//            if(result != 1){
//                throw new BaseException(INVALID_POST);
//            }
//
//        }
//


    
//    // 중복
//
//        // 중복된 이메일
//        public static  void isEmailDuplicated(String email) throws BaseException{
//
//            if(!isRegexEmail(email))
//                throw new BaseException(DUPLICATED_EMAIL);
//
//        }
//
//        // 가입된 핸드폰 번호
//        public static  void isPhoneDuplicated(String phone) throws BaseException{
//
//            if(!isRegexPhone(phone))
//                throw new BaseException(DUPLICATED_PHONE);
//
//        }





}
