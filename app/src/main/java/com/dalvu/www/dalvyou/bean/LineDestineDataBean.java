package com.dalvu.www.dalvyou.bean;

import java.util.List;

/**
 * 线路提交订单页面的bean类
 * Created by user on 2017/6/26.
 */

public class LineDestineDataBean {

    /**
     * status : 00000
     * msg : 成功
     * agencyInfo : {"id":"295","name":"杨小毛","mobile":"13126997216","account_state":"1"}
     * list : {"id":"4378","name":"西欧U6 盈尚 一价全含 德法意瑞14天","price_child_agency":"1350000","price_adult_agency":"1350000","price_hotel_agency":"50000"}
     * tour_date : [{"id":"96612","start_time":"2017-07-08 00:00:00"}]
     */

    public String status;
    public String msg;
    public AgencyInfoBean agencyInfo;
    public ListBean list;
    public List<TourDateBean> tour_date;

    public static class AgencyInfoBean {
        /**
         * id : 295
         * name : 杨小毛
         * mobile : 13126997216
         * account_state : 1
         */

        public String id;
        public String name;
        public String mobile;
        public String account_state;
    }

    public static class ListBean {
        /**
         * id : 4378
         * name : 西欧U6 盈尚 一价全含 德法意瑞14天
         * price_child_agency : 1350000
         * price_adult_agency : 1350000
         * price_hotel_agency : 50000
         */

        public String id;
        public String name;
        public String price_child_agency;
        public String price_adult_agency;
        public String price_hotel_agency;
    }

    public static class TourDateBean {
        /**
         * id : 96612
         * start_time : 2017-07-08 00:00:00
         */

        public String id;
        public String start_time;
    }
}
