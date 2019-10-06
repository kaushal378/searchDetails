package com.HarrisburgU.searchDetails.pojos;

public class StringUtils {
    public static boolean isEmpty(String in){
        if(in==null || in.equals("")){
            return true;
        }

        return false;
    }

    public static boolean isNotEmpty(String in){
        return !isEmpty(in);
    }
}
