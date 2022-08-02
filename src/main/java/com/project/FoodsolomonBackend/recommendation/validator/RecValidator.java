package com.project.FoodsolomonBackend.recommendation.validator;

import com.project.FoodsolomonBackend.config.exception.BaseException;

import java.util.Arrays;

import static com.project.FoodsolomonBackend.config.exception.BaseResponseStatus.*;
import static com.project.FoodsolomonBackend.utils.FormalValidationException.*;

public class RecValidator {


    public static void validatedUserInput(String email, String password, String nickname, String ageRange) throws BaseException {

        String[] ageRangeArr = {"1~9", "10~14", "15~19", "20~29", "30~39", "40~49", "50~59",
                                "60~69", "70~79", "80~89", "90"};

        if (checkEmailFormal(email)) {
            throw new BaseException(INVALID_EMAIL);
        }

        if (checkPhoneFormal(password)) {
            throw new BaseException(INVALID_PWD);
        }

        if (checkNameFormal(nickname)) {
            throw new BaseException(INVALID_NICKNAME);
        }

        if (!(Arrays.stream(ageRangeArr).anyMatch(s -> s.equals(ageRange)))){
            throw new BaseException(POST_MEMBERS_INVALID_AGE_RANGE);
        }

    }

}
