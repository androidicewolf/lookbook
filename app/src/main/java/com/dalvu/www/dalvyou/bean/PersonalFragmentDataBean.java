package com.dalvu.www.dalvyou.bean;

/**
 * 个人中心数据的bean类
 * Created by wolf on 17.7.2.
 */

public class PersonalFragmentDataBean {
    /**
     * agencyInfo : {"column_sort":"3,1,5,4,7,2,6,8","head_pic":"http://file.m.lydlr.com/160/160/agency/20170117/587dcb30c43c3.jpg","id":"295","mobile":"13126997216","name":"杨小毛","operator_id":"1"}
     * msg : 成功
     * sign_token : c34e77f41d0be7b4c22ac293627a6d6a
     * status : 00000
     */

    public AgencyInfoBean agencyInfo;
    public String msg;
    public String sign_token;
    public String status;

    public static class AgencyInfoBean {
        /**
         * column_sort : 3,1,5,4,7,2,6,8
         * head_pic : http://file.m.lydlr.com/160/160/agency/20170117/587dcb30c43c3.jpg
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
}
