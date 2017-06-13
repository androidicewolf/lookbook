package com.dalvu.www.dalvyou.tools;

import java.util.ArrayList;

/**自定义的变量
 * Created by user on 2017/5/9.
 */

public interface CustomValue {
    /**
     * 账号
     **/
    public static final String NAME = "name";
    /**
     * 密码
     **/
    public static final String PASSWORD = "password";
    /**
     * 第一次登陆
     **/
    public static final String ISFIRST = "isFirst";
    /**
     * 登录用户的类型
     **/
    public static final String STATUSTYPE = "statusType";
    /**
     * 服务器地址
     **/
    public static final String SERVER = "http://dalvuapi.dalvu.com";
    /**首页模块分类请求网络的what**/
    public static final int HOMECOLUMN = 101;
    /**首页线路列表请求网络的what**/
    public static final int HOMELINE = 102;
    /**线路详情基本信息请求网络的what**/
    public static final int LINEDETAILBASE = 103;
    /**线路详情“行程安排”请求网络的what**/
    public static final int LINEDPLAN = 104;
    /**线路详情“产品亮点”请求网络的what**/
    public static final int LINEDESCRIPTION = 105;
    /**线路详情“费用说明”请求网络的what**/
    public static final int LINECOST = 106;
    /**线路详情“注意事项”请求网络的what**/
    public static final int LINENOTICE = 107;
    //应用中用到的城市
    public static final ArrayList<String> CITYS = new ArrayList<>();
}
