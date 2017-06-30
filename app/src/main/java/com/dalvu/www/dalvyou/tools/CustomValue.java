package com.dalvu.www.dalvyou.tools;

import java.util.ArrayList;

/**自定义的变量
 * Created by user on 2017/5/9.
 */

public interface CustomValue {
    /**
     * 用户的ID
     */
    public static final String UID = "uid";
    /**
     * 存储的用户的token
     */
    public static final String TOKEN = "sign_token";

    /**
     * 用户的type类型
     */
    public static final String TYPE = "user_type";
    /**
     * 第一次登陆
     **/
    public static final String ISFIRST = "isFirst";

//    /**
//     * 用户登录的账号
//     */
//    public static final String USERNAME = "userName";
//    /**
//     * 用户的登录密码
//     */
//    public static final String PASSWORD = "password";

    /**
     * 服务器地址
     **/
    public static final String SERVER = "http://demoapi.dalvu.com/index.php/";
    /**首页模块分类请求网络的what**/
    public static final int HOMECOLUMN = 101;
    /**首页线路列表请求网络的what**/
    public static final int HOMELINE = 102;
    /**线路详情基本信息请求网络的what**/
    public static final int LINEDETAILBASE = 103;
    /**线路详情“行程安排”请求网络的what**/
    public static final int LINEPLAN = 104;
    /**线路详情“产品亮点”请求网络的what**/
    public static final int LINEDESCRIPTION = 105;
    /**线路详情“费用说明”请求网络的what**/
    public static final int LINECOST = 106;
    /**线路详情“注意事项”请求网络的what**/
    public static final int LINENOTICE = 107;
    //应用中用到的城市
    public static final ArrayList<String> CITYS = new ArrayList<>();
}
