package com.project.FoodsolomonBackend.user.validator;

import com.project.FoodsolomonBackend.config.exception.BaseException;
import com.project.FoodsolomonBackend.config.exception.BaseResponseStatus;
import com.project.FoodsolomonBackend.user.dto.PostUserReq;

import javax.persistence.Column;

import java.util.Arrays;

import static com.project.FoodsolomonBackend.config.exception.BaseResponseStatus.*;
import static com.project.FoodsolomonBackend.utils.FormalValidationException.*;

public class UserValidator {


    public static void validatedUserInput(String email, String nickname) throws BaseException {


        if (checkEmailFormal(email)) {
            throw new BaseException(INVALID_EMAIL);
        }



        if (checkNameFormal(nickname)) {
            throw new BaseException(INVALID_NICKNAME);
        }


    }

}
