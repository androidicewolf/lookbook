package com.dalvu.www.dalvyou.bean;

import java.util.List;

/**保存线路详情页基本信息的bean类
 * Created by user on 2017/5/19.
 */

public class LineDetailDatabean {


    /**
     * status : 00000
     * msg : 成功
     * departure :
     * tourSkuDate : null
     * totalDay : 5
     * list : {"id":"4005","name":"四星芽庄4晚5天","provider_name":"一块去旅游","contact_person":"一块去旅游","min_price":"268000","destinations":"东南亚","traffic_go":"飞机","traffic_back":"飞机","pics":"#file1#/tour/2016/1230/01547765828.jpg|0|@@@"}
     * picArr : ["http://file1.lydlr.com/c/563/234/tour/2016/1230/01547765828.jpg"]
     */

    public String status;
    public String msg;
    public String departure;
    public Object tourSkuDate;
    public String totalDay;
    public ListBean list;
    public List<String> picArr;

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
    }
}
