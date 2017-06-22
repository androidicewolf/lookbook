package com.dalvu.www.dalvyou.bean;

import java.util.List;

/**首页头布局数据的base类
 * Created by user on 2017/5/18.
 */

public class HomeFragmentModuleDataBean {
    /**
     * agencyInfo : {"column_sort":"1,5,8,6,4,7,2,3","head_pic":"http://file.m.lydlr.com/160/160/agency/20170613/593fb52742c31.jpg","id":"295","mobile":"13126997216","name":"杨小毛","operator_id":"1"}
     * columnList : [{"icon":"1","name":"国内游","pic_icon":"http://cdn.lydlr.com/public/images/modular/domestic_tourism.png","sort":"1","type":"1","url":"http://dalvuapi.dalvu.com/index.php/Api/Outbound/index"},{"icon":"2","name":"门票","pic_icon":"http://cdn.lydlr.com/public/images/modular/home_admission_ticket.png","sort":"7","type":"7","url":"/index.php/TouristApi/Ticket/index.html"},{"icon":"3","name":"特价","pic_icon":"http://cdn.lydlr.com/public/images/modular/special_offe.png","sort":"8","type":"8","url":"http://dalvuapi.dalvu.com/index.php/Api/Outbound/index"},{"icon":"4","name":"保险/签证","pic_icon":"http://cdn.lydlr.com/public/images/modular/home_insurance_visa.png","sort":"5","type":"5","url":"/index.php/TouristApi/Visa/InsuranceVisa.html"},{"icon":"5","name":"出境游","pic_icon":"http://cdn.lydlr.com/public/images/modular/outbound_tourism.png","sort":"2","type":"2","url":"http://dalvuapi.dalvu.com/index.php/Api/Outbound/index"},{"icon":"6","name":"自由行","pic_icon":"http://cdn.lydlr.com/public/images/modular/free_walker.png","sort":"4","type":"4","url":"http://dalvuapi.dalvu.com/index.php/Api/Outbound/index"},{"icon":"7","name":"机票","pic_icon":"http://cdn.lydlr.com/public/images/modular/passenger_ticket.png","sort":"6","type":"6","url":"/index.php/TouristApi/WxJsAPI/index.html"},{"icon":"8","name":"周边游","pic_icon":"http://cdn.lydlr.com/public/images/modular/surrounding_tourism.png","sort":"3","type":"3","url":"http://dalvuapi.dalvu.com/index.php/Api/Outbound/index"}]
     * msg : 成功
     * sign_token : a527eacde6cd1009cb71944622448c24
     * status : 00000
     * touristInfo : {"head_img":"0","id":"12","mobile":"18310084054","name":"刘振兴","the_agency_id":"295"}
     */

    public AgencyInfoBean agencyInfo;
    public String msg;
    public String sign_token;
    public String status;
    public TouristInfoBean touristInfo;
    public List<ColumnListBean> columnList;

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
         * head_img : 0
         * id : 12
         * mobile : 18310084054
         * name : 刘振兴
         * the_agency_id : 295
         */

        public String head_img;
        public String id;
        public String mobile;
        public String name;
        public String the_agency_id;
    }

    public static class ColumnListBean {
        /**
         * icon : 1
         * name : 国内游
         * pic_icon : http://cdn.lydlr.com/public/images/modular/domestic_tourism.png
         * sort : 1
         * type : 1
         * url : http://dalvuapi.dalvu.com/index.php/Api/Outbound/index
         */

        public String icon;
        public String name;
        public String pic_icon;
        public String sort;
        public String type;
        public String url;
    }
}
