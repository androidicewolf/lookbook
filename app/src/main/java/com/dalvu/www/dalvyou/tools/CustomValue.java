package com.dalvu.www.dalvyou.tools;

/**自定义的变量
 * Created by user on 2017/5/9.
 */

public interface CustomValue {
    /**第一次登陆**/
    public static final String isFirst = "isFirst";
    /**服务器地址**/
    public static final String Server = "http://dalvuapi.dalvu.com";
    /**首页模块分类请求网络的what**/
    public static final int HOMECOLUMN = 1;
    /**首页线路列表请求网络的what**/
    public static final int HOMELINE = 2;
    /**线路详情基本信息请求网络的what**/
    public static final int LINEDETAILBASE = 3;
    /**线路详情“行程安排”请求网络的what**/
    public static final int LINEDPLAN = 4;
    /**线路详情“产品亮点”请求网络的what**/
    public static final int LINEDESCRIPTION = 5;
    /**线路详情“费用说明”请求网络的what**/
    public static final int LINECOST = 6;
    /**线路详情“注意事项”请求网络的what**/
    public static final int LINENOTICE = 7;
}
