package com.project.FoodsolomonBackend.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationRegex {
    public static boolean isRegexEmail(String target) {
        String regex = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(target);
        return matcher.find();
    }

    public static boolean isRegexPhone(String target){
        String regex = "^01(?:0|1[6-9])-\\d{3,4}-\\d{4}$"; // 01 다음에 오는 값으로, 0,1,6~9 만 가능
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(target);
        return matcher.find();
    }

    public static boolean isRegexName(String target){
        String regex = "^[a-zA-Z0-9가-힣]*$"; // 한글, 영어, 숫자만
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(target);
        return matcher.find();
    }


    public static boolean isRegexUrl(String target){

        String regex =
                "^((((https?|ftps?|gopher|telnet|nntp)://)|(mailto:|news:))" +
                        "(%[0-9A-Fa-f]{2}|[-()_.!~*';/?:@&=+$,A-Za-z0-9])+)" +
                        "([).!';/?:,][[:blank:]])?$";

        Pattern URL_PATTERN = Pattern.compile(regex);
        Matcher matcher = URL_PATTERN.matcher(target);
        return matcher.find();
    }

    public static boolean isRegexPwd(String target){
        String regex = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$"; // 8자 이상의 영어, 특수문자, 숫자만
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(target);
        return matcher.find();


    }

    public static boolean isRegexBirth(String target){
        String regex = "^(19[0-9][0-9]|20\\d{2})-(0[0-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(target);
        return matcher.find();


    }

    public static boolean isRegexNumber(String target){
        String regex = "[0-9]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(target);
        return matcher.find();


    }


}

