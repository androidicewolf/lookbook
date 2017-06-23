package com.dalvu.www.dalvyou.bean;

import java.util.List;

/**保存线路详情页基本信息的bean类
 * Created by user on 2017/5/19.
 */

public class LineDetailDatabean {

    /**
     * status : 00000
     * mobile : 13126997216
     * msg : 成功
     * tourSkuDate : [{"id":"94586","price_adult_list":"1329900","start_time":"2017-06-16","price_adult_agency":"1229900"},{"id":"94585","price_adult_list":"1329900","start_time":"2017-06-02","price_adult_agency":"1229900"}]
     * totalDay : 9
     * list : {"id":"4302","name":"莫斯科+圣彼得堡+喀琅施塔得+新西伯利亚动物园+贝加尔湖欧亚全景9日行程","provider_name":"浪漫俄罗斯","contact_person":"吕亚勤","min_price":"1329900","destinations":"俄罗斯","traffic_go":"飞机","traffic_back":"飞机","pics":"#file1#/tour/2017/0408/01324231736.jpg|0|@@@#file1#/tour/2017/0408/00264932437.jpg|0|@@@","departure":"北京","totalDay":"9"}
     * picArr : ["http://file1.lydlr.com/c/563/234/tour/2017/0408/01324231736.jpg","http://file1.lydlr.com/c/563/234/tour/2017/0408/00264932437.jpg"]
     */

    public String status;
    public String mobile;
    public String msg;
    public String totalDay;
    public ListBean list;
    public List<TourSkuDateBean> tourSkuDate;
    public List<String> picArr;

    public static class ListBean {
        /**
         * id : 4302
         * name : 莫斯科+圣彼得堡+喀琅施塔得+新西伯利亚动物园+贝加尔湖欧亚全景9日行程
         * provider_name : 浪漫俄罗斯
         * contact_person : 吕亚勤
         * min_price : 1329900
         * destinations : 俄罗斯
         * traffic_go : 飞机
         * traffic_back : 飞机
         * pics : #file1#/tour/2017/0408/01324231736.jpg|0|@@@#file1#/tour/2017/0408/00264932437.jpg|0|@@@
         * departure : 北京
         * totalDay : 9
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
        public String departure;
        public String totalDay;
    }

    public static class TourSkuDateBean {
        /**
         * id : 94586
         * price_adult_list : 1329900
         * start_time : 2017-06-16
         * price_adult_agency : 1229900
         */

        public String id;
        public String price_adult_list;
        public String start_time;
        public String price_adult_agency;
    }
}
