package com.wwc.ypt.utils.constants;

public enum LoginEnum {
    LoginToken("token");
    private String token;

    LoginEnum(String token) {
        this.token=token;
    }
   public String getToken(){
        return token;
   }
}
