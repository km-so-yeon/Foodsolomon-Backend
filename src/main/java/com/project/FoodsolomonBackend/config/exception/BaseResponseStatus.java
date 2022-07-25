package com.project.FoodsolomonBackend.config.exception;

import lombok.Getter;

/**
 * 에러 코드 관리
 */
@Getter
public enum BaseResponseStatus {




    /**
     * 1000 : 요청 성공
     */
    SUCCESS(true, 1000, "요청에 성공하였습니다."),
    // search
    SEARCH_RESULT_EMPTY(true,1001, "검색 결과가 없습니다."),
    BASKET_RESULT_EMPTY(true,1002, "장바구니가 없습니다."),

    
    /**
     * 2000 : Request 오류
     */
    // Common
    REQUEST_ERROR(false, 2000, "입력값을 확인해주세요."),
    EMPTY_JWT(false, 2001, "JWT를 입력해주세요."),
    INVALID_JWT(false, 2002, "유효하지 않은 JWT입니다."),
    INVALID_MEMBER_JWT(false,2003,"권한이 없는 유저의 접근입니다."),
    INVALID_EMAIL(false, 2004, "이메일 형식을 확인해주세요."),
    INVALID_PWD(false,2005,"비밀번호 형식을 확인해주세요."),



    // register
    POST_MEMBERS_DUPLICATED_EMAIL(false, 2016, "중복된 이메일입니다."),
    POST_MEMBERS_EMPTY_AGREEPERSONAL(false, 2017, "개인정보 수집 동의 필요"),
    POST_MEMBERS_EMPTY_EMAIL(false, 2018, "이메일을 입력해주세요"),
    POST_MEMBERS_EMPTY_NAME(false, 2019, "닉네임을 입력해주세요"),
    POST_MEMBERS_EMPTY_PWD(false, 2020, "비밀번호 입력해주세요"),
    POST_MEMBERS_DIFFERENT_PWD(false, 2021, "비밀번호와 비밀번호 확인이 다릅니다."),

    POST_BOOKMARK_EMPTY_MEMBER(false, 2022, "회원가입 후 북마크가 가능합니다."),
 
    /**
     * 3000 : Response 오류
     */
    // Common
    RESPONSE_ERROR(false, 3000, "값을 불러오는데 실패하였습니다."),
    INVALID_MEMBER(false, 3001, "존재하지않는 회원입니다."),
    BLOCKED_MEMBER(false,3002, "삭제되거나 이용할 수 없는 회원입니다."),

    // register

    FAILED_TO_REGISTER(false,3011, "회원가입에 실패했습니다."),



    /**
     * 4000 : Database, Server 오류
     */
    DATABASE_ERROR(false, 4000, "데이터베이스 연결에 실패하였습니다."),
    SERVER_ERROR(false, 4001, "서버와의 연결에 실패하였습니다."),

    PASSWORD_ENCRYPTION_ERROR(false, 4011, "비밀번호 암호화에 실패하였습니다."),
    PASSWORD_DECRYPTION_ERROR(false, 4012, "비밀번호 복호화에 실패하였습니다.");




    private final boolean isSuccess;
    private final int code;
    private final String message;

    private BaseResponseStatus(boolean isSuccess, int code, String message) {
        this.isSuccess = isSuccess;
        this.code = code;
        this.message = message;
    }
}
