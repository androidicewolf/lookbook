package com.dalvu.www.dalvyou.bean;

/**
 * 验证用户本地存储的token合法性的bean类
 * Created by user on 2017/6/20.
 */

public class UserInfoBean {


    public String msg;
    public String sign_token;
    public String status;
    /**
     * agencyInfo : {"column_sort":"1,5,8,6,4,7,2,3","head_pic":"http://file.m.lydlr.com/160/160/agency/20170613/593fb52742c31.jpg","id":"295","mobile":"13126997216","name":"杨小毛","operator_id":"1"}
     * msg : 成功
     * sign_token : dc1fa1aabcbe5e7faeb90cf8cfaa4cf5
     * status : 00000
     */
    //顾问的个人信息
    public AgencyInfoBean agencyInfo;
    //C客户的个人信息
    public TouristInfoBean touristInfo;

    public static class AgencyInfoBean {
        /**
         * column_sort : 1,5,8,6,4,7,2,3
         * head_pic : http://file.m.lydlr.com/160/160/agency/20170613/593fb52742c31.jpg
         * id : 295
         * mobile : 13126997216
         * name : 杨小毛
         * operator_id : 1
         */
        public String column_sort;
        public String head_pic;
        public String id;
        public String mobile;
        public String name;
        public String operator_id;
    }

    public static class TouristInfoBean {
        /**
         * head_img : http://file.m.lydlr.com/160/160/tourist0
         * id : 12
         * mobile : 18310084054
         * name : 刘振兴
         * operator_id : 1
         */

        public String head_img;
        public String id;
        public String mobile;
        public String name;
        public String operator_id;
    }
}
