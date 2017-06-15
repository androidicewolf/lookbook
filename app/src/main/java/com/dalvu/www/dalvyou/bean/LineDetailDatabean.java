package com.dalvu.www.dalvyou.bean;

import java.io.Serializable;
import java.util.List;

/**保存线路详情页基本信息的bean类
 * Created by user on 2017/5/19.
 */

public class LineDetailDatabean {
    /**
     * list : {"contact_person":"畅游天下","departure":"河北 石家庄","destinations":"华东","id":"4504","min_price":"199000","name":"遇见江南双卧六日游","pics":"#file1#/tour/2017/0508/00752320811.jpg|0|@@@#file1#/tour/2017/0508/01929569157.jpg|0|@@@","provider_name":"畅游天下","totalDay":"6","traffic_back":"火车","traffic_go":"火车"}
     * mobile :
     * msg : 成功
     * picArr : ["http://file1.lydlr.com/c/563/234/tour/2017/0508/00752320811.jpg","http://file1.lydlr.com/c/563/234/tour/2017/0508/01929569157.jpg"]
     * status : 00000
     * tourSkuDate : [{"id":"100727","price_adult_agency":"169000","price_adult_list":"199000","start_time":"2017-11-10"},{"id":"100726","price_adult_agency":"169000","price_adult_list":"199000","start_time":"2017-11-06"},{"id":"100725","price_adult_agency":"169000","price_adult_list":"199000","start_time":"2017-11-03"},{"id":"100724","price_adult_agency":"169000","price_adult_list":"199000","start_time":"2017-10-30"},{"id":"100723","price_adult_agency":"169000","price_adult_list":"199000","start_time":"2017-10-27"},{"id":"100717","price_adult_agency":"169000","price_adult_list":"199000","start_time":"2017-10-23"},{"id":"100722","price_adult_agency":"169000","price_adult_list":"199000","start_time":"2017-10-20"},{"id":"100721","price_adult_agency":"169000","price_adult_list":"199000","start_time":"2017-10-16"},{"id":"100720","price_adult_agency":"169000","price_adult_list":"199000","start_time":"2017-10-13"},{"id":"100719","price_adult_agency":"169000","price_adult_list":"199000","start_time":"2017-10-09"},{"id":"100718","price_adult_agency":"169000","price_adult_list":"199000","start_time":"2017-10-06"},{"id":"100675","price_adult_agency":"169000","price_adult_list":"199000","start_time":"2017-10-02"},{"id":"100710","price_adult_agency":"169000","price_adult_list":"199000","start_time":"2017-09-29"},{"id":"100715","price_adult_agency":"169000","price_adult_list":"199000","start_time":"2017-09-25"},{"id":"100716","price_adult_agency":"169000","price_adult_list":"199000","start_time":"2017-09-22"},{"id":"100714","price_adult_agency":"169000","price_adult_list":"199000","start_time":"2017-09-18"},{"id":"100712","price_adult_agency":"169000","price_adult_list":"199000","start_time":"2017-09-15"},{"id":"100713","price_adult_agency":"169000","price_adult_list":"199000","start_time":"2017-09-11"},{"id":"100711","price_adult_agency":"169000","price_adult_list":"199000","start_time":"2017-09-08"},{"id":"100701","price_adult_agency":"169000","price_adult_list":"199000","start_time":"2017-09-04"},{"id":"100709","price_adult_agency":"169000","price_adult_list":"199000","start_time":"2017-09-01"},{"id":"100704","price_adult_agency":"169000","price_adult_list":"199000","start_time":"2017-08-28"},{"id":"100707","price_adult_agency":"169000","price_adult_list":"199000","start_time":"2017-08-25"},{"id":"100705","price_adult_agency":"169000","price_adult_list":"199000","start_time":"2017-08-21"},{"id":"100706","price_adult_agency":"169000","price_adult_list":"199000","start_time":"2017-08-18"},{"id":"100708","price_adult_agency":"169000","price_adult_list":"199000","start_time":"2017-08-14"},{"id":"100703","price_adult_agency":"169000","price_adult_list":"199000","start_time":"2017-08-11"},{"id":"100702","price_adult_agency":"169000","price_adult_list":"199000","start_time":"2017-08-07"},{"id":"100674","price_adult_agency":"169000","price_adult_list":"199000","start_time":"2017-08-04"},{"id":"100700","price_adult_agency":"169000","price_adult_list":"199000","start_time":"2017-07-31"},{"id":"100699","price_adult_agency":"169000","price_adult_list":"199000","start_time":"2017-07-28"},{"id":"100698","price_adult_agency":"169000","price_adult_list":"199000","start_time":"2017-07-24"},{"id":"100696","price_adult_agency":"169000","price_adult_list":"199000","start_time":"2017-07-21"},{"id":"100697","price_adult_agency":"169000","price_adult_list":"199000","start_time":"2017-07-17"},{"id":"100695","price_adult_agency":"169000","price_adult_list":"199000","start_time":"2017-07-14"},{"id":"100694","price_adult_agency":"169000","price_adult_list":"199000","start_time":"2017-07-10"},{"id":"100693","price_adult_agency":"169000","price_adult_list":"199000","start_time":"2017-07-07"},{"id":"100690","price_adult_agency":"169000","price_adult_list":"199000","start_time":"2017-07-03"},{"id":"100691","price_adult_agency":"169000","price_adult_list":"199000","start_time":"2017-06-30"},{"id":"100692","price_adult_agency":"169000","price_adult_list":"199000","start_time":"2017-06-26"},{"id":"100689","price_adult_agency":"169000","price_adult_list":"199000","start_time":"2017-06-23"},{"id":"100688","price_adult_agency":"169000","price_adult_list":"199000","start_time":"2017-06-19"},{"id":"100687","price_adult_agency":"169000","price_adult_list":"199000","start_time":"2017-06-16"}]
     */

    public ListBean list;
    public String mobile;
    public String msg;
    public String status;
    public List<String> picArr;
    public List<TourSkuDateBean> tourSkuDate;

    public static class ListBean {
        /**
         * contact_person : 畅游天下
         * departure : 河北 石家庄
         * destinations : 华东
         * id : 4504
         * min_price : 199000
         * name : 遇见江南双卧六日游
         * pics : #file1#/tour/2017/0508/00752320811.jpg|0|@@@#file1#/tour/2017/0508/01929569157.jpg|0|@@@
         * provider_name : 畅游天下
         * totalDay : 6
         * traffic_back : 火车
         * traffic_go : 火车
         */

        public String contact_person;
        public String departure;
        public String destinations;
        public String id;
        public String min_price;
        public String name;
        public String pics;
        public String provider_name;
        public String totalDay;
        public String traffic_back;
        public String traffic_go;
    }

    public static class TourSkuDateBean implements Serializable {
        /**
         * id : 100727
         * price_adult_agency : 169000
         * price_adult_list : 199000
         * start_time : 2017-11-10
         */

        public String id;
        public String price_adult_agency;
        public String price_adult_list;
        public String start_time;
    }
}
