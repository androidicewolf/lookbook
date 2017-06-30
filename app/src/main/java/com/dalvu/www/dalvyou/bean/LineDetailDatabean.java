package com.dalvu.www.dalvyou.bean;

import java.io.Serializable;
import java.util.List;

/**保存线路详情页基本信息的bean类
 * Created by user on 2017/5/19.
 */

public class LineDetailDatabean {

    /**
     * status : 00000
     * msg : 成功
     * agencyTourTitle : {"id":"30","title":"好好好"}
     * tourSkuDate : null
     * totalDay : null
     * list : {"id":"4005","name":"四星芽庄4晚5天","provider_name":"一块去旅游","contact_person":"一块去旅游","min_price":"268000","destinations":"东南亚","traffic_go":"飞机","traffic_back":"飞机","pics":"#file1#/tour/2016/1230/01547765828.jpg|0|@@@","contact_phone":"18500024060","departure":"北京","totalDay":"5"}
     * picArr : ["http://file1.lydlr.com/c/563/234/tour/2016/1230/01547765828.jpg"]
     * sign_token : b8912bbd73a6af6e9376d178b7fc8c1a
     */

    public String status;
    public String msg;
    public AgencyTourTitleBean agencyTourTitle;
    public List<TourSkuDateBean> tourSkuDate;
    public String totalDay;
    public ListBean list;
    public String sign_token;
    public List<String> picArr;

    /**
     * agencyTourTitle : null
     * tourSkuDate : [{"id":"96612","price_adult_list":"1800000","start_time":"2017-07-08","price_adult_agency":"1350000"}]
     * totalDay : null
     */

    public static class AgencyTourTitleBean {
        /**
         * id : 30
         * title : 好好好
         */

        public String id;
        public String title;
    }

    public static class ListBean {
        /**
         * id : 4005
         * name : 四星芽庄4晚5天
         * provider_name : 一块去旅游
         * contact_person : 一块去旅游
         * min_price : 268000
         * destinations : 东南亚
         * traffic_go : 飞机
         * traffic_back : 飞机
         * pics : #file1#/tour/2016/1230/01547765828.jpg|0|@@@
         * contact_phone : 18500024060
         * departure : 北京
         * totalDay : 5
         */

        public String id;
        public String name;
        public String provider_name;
        public String contact_person;
        public String min_price;
        public String destinations;
        public String traffic_go;
        public String traffic_back;
        public String pics;
        public String contact_phone;
        public String departure;
        public String totalDay;
    }

    public static class TourSkuDateBean implements Serializable {
        /**
         * id : 96612
         * price_adult_list : 1800000
         * start_time : 2017-07-08
         * price_adult_agency : 1350000
         */

        public String id;
        public String price_adult_list;
        public String start_time;
        public String price_adult_agency;
    }
}
