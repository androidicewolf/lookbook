package com.dalvu.www.dalvyou.bean;

import java.util.List;

/**
 * 线路改价的bean类
 * Created by user on 2017/6/26.
 */

public class LineChangePriceDataBean {
    /**
     * status : 00000
     * msg : 成功
     * list : [{"id":"96612","tour_id":"4378","start_time":"2017-07-08","price_child_agency":"1350000","price_adult_agency":"1350000","price_hotel_list":"56000","price_hotel_agency":"50000","price_child_list":"1580000","price_adult_list":"1580000","cruise":null,"user_defined_adult_list":"1800000","user_defined_child_list":"1800000","user_defined_hotel_list":"1000000"}]
     * tour_list : {"id":"4378","name":"西欧U6 盈尚 一价全含 德法意瑞14天","min_price":"1800000","cover_pic":"http://file1.lydlr.com/c/240/160/tour/2017/0414/01173481906.jpg","line_type":"3","travel_type":"1","lineTypeName":"出境游"}
     * sign_token : b5d7cecd3a82650c13479aff5ae0ca5b
     */

    public String status;
    public String msg;
    public TourListBean tour_list;
    public String sign_token;
    public List<ListBean> list;

    public static class TourListBean {
        /**
         * id : 4378
         * name : 西欧U6 盈尚 一价全含 德法意瑞14天
         * min_price : 1800000
         * cover_pic : http://file1.lydlr.com/c/240/160/tour/2017/0414/01173481906.jpg
         * line_type : 3
         * travel_type : 1
         * lineTypeName : 出境游
         */

        public String id;
        public String name;
        public String min_price;
        public String cover_pic;
        public String line_type;
        public String travel_type;
        public String lineTypeName;
    }

    public static class ListBean {
        /**
         * id : 96612
         * tour_id : 4378
         * start_time : 2017-07-08
         * price_child_agency : 1350000
         * price_adult_agency : 1350000
         * price_hotel_list : 56000
         * price_hotel_agency : 50000
         * price_child_list : 1580000
         * price_adult_list : 1580000
         * cruise : null
         * user_defined_adult_list : 1800000
         * user_defined_child_list : 1800000
         * user_defined_hotel_list : 1000000
         */

        public String id;
        public String tour_id;
        public String start_time;
        public String price_child_agency;
        public String price_adult_agency;
        public String price_hotel_list;
        public String price_hotel_agency;
        public String price_child_list;
        public String price_adult_list;
        public String cruise;
        public String user_defined_adult_list;
        public String user_defined_child_list;
        public String user_defined_hotel_list;
    }
}
