package com.example.daohvph35768_ass.Preferences;

import android.content.Context;
import android.content.SharedPreferences;

public class UserPreference {
    //create biến
    private static final String KEY_LOGIN_STATE = "key_login_state";
    private final SharedPreferences sharedPreferences;
    //create constructor
    public UserPreference(Context context) {
        //call
        sharedPreferences = context.getSharedPreferences("user_preference",Context.MODE_PRIVATE);
    }

    //is login if true and not login if false
    public boolean isLogin(){
        return sharedPreferences.getBoolean(KEY_LOGIN_STATE,false);
    }

    //set login state
    public void setLogin(boolean state){
        sharedPreferences.edit().putBoolean(KEY_LOGIN_STATE,state).apply();
    }

    //logout
    public void logout(){
        sharedPreferences.edit().clear().apply();
    }
}
