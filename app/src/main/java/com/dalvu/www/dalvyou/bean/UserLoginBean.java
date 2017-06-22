package com.dalvu.www.dalvyou.bean;

/**
 * 用户登录时服务器返回的数据bean类(包括C客户登录和顾问登录)
 * Created by user on 2017/6/20.
 */

public class UserLoginBean {
    /**
     * binding_state : 1
     * msg : 成功
     * sign_token : d075f271721169ca229fe45e1a47c0a6
     * status : 00000
     * uid : 1305
     * user_type : 5
     */

    public String binding_state;
    public String msg;
    public String sign_token;
    public String status;
    public String uid;
    public String user_type;
}
