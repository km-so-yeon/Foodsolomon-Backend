package com.project.FoodsolomonBackend.utils;


import static com.project.FoodsolomonBackend.utils.ValidationRegex.*;



public class FormalValidationException {

    // 1. 값이 있는가?
    // 2. String이라면 빈칸인가?
        // 빈칸이어도 되는 부분은 checkEmpty 활용
    // 3. 닉네임, 이메일, 휴대폰 등의 형식이 맞는가?

    // 빈값 여부 유효성
    // Integer를 사용하는 이유는 유효성 검사를 위해 null인지 여부를 확인할 때, null을 담을 수가 있으니

    // null 인지 확인하느 녀석
    // "" 이나 "  "인지 확인하는 녀석
    // 너무 길지 않는지 확인하는 녀석



    public static boolean hasRequestValue(Object... obj){

        for(Object x: obj) {

            if (isEmpty(x)) {
                return !(isEmpty(x));
            }


        }

        return true;

    }

    public static boolean isNotEmptyValue(Object... obj){

        for(Object x: obj) {

            if(x.getClass() == String.class){
                return !(isEmptyString((String)x));
            }

        }

        return true;

    }


    // Common

    public static boolean isEmpty(Object obj){

        boolean result = (obj == null);

        return result;
    }

    public static boolean isEmptyString(String s){

        boolean result = (s.trim().equals(""));

        return result;
    }


    public static boolean isNotTooLong(Object... obj){

        for(Object x: obj) {

            if(x.getClass() == String.class){
                return !(checkValueLength(((String)(x)).trim()));
            }
        }

        return true;

    }



    // 형식 유효성
    
    public static boolean checkIdFormal(Long id){


        return (id>0);
    }


    public static boolean checkPwdFormal(String pwd){

        return !isRegexPwd(pwd);
    }


    public static boolean checkPhoneFormal(String phone){


        return !(isRegexPhone(phone));
    }

    public static boolean checkEmailFormal(String email){


        return !(isRegexEmail(email));
    }

    public static boolean checkURLFormal(String url){


        return !(isRegexUrl(url));
    }

    public static boolean checkBirthFormal(String birth){


        return !(isRegexBirth(birth));
    }

    public static boolean checkNameFormal(String name){


        return !(isRegexName(name));
    }

    public static boolean checkNumberFormal(String num){


        return !(isRegexNumber(num));
    }



    // 길이 유효성

    public static boolean checkValueLength(String value){

        value = value.trim();

        return !(value.length()<=30);
    }


    public static boolean checkTextLength(String text){

        text = text.trim();

        return !(text.length()<=1000);
    }

    public static boolean checkUrlLength(String url){

        url = url.trim();

        return !(url.length()<=2084);
    }



}
