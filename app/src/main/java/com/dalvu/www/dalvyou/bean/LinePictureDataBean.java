package com.dalvu.www.dalvyou.bean;

import java.util.List;

/**
 * 线路详情页目的地图册数据的bean类
 * Created by user on 2017/6/28.
 */

public class LinePictureDataBean {

    /**
     * list : {"id":"4509","name":"迷情乌镇双卧五日游","pics":"#file1#/tour/2017/0509/01735578334.jpg|0|@@@#file1#/tour/2017/0509/01464804439.jpg|0|@@@"}
     * msg : 成功
     * picArr : [{"img":"http://file1.lydlr.com/600/800/tour/2017/0509/01735578334.jpg","name":false},{"img":"http://file1.lydlr.com/600/800/tour/2017/0509/01464804439.jpg","name":false}]
     * status : 00000
     */

    public ListBean list;
    public String msg;
    public String status;
    public List<PicArrBean> picArr;

    public static class ListBean {
        /**
         * id : 4509
         * name : 迷情乌镇双卧五日游
         * pics : #file1#/tour/2017/0509/01735578334.jpg|0|@@@#file1#/tour/2017/0509/01464804439.jpg|0|@@@
         */

        public String id;
        public String name;
        public String pics;
    }

    public static class PicArrBean {
        /**
         * img : http://file1.lydlr.com/600/800/tour/2017/0509/01735578334.jpg
         * name : false
         */

        public String img;
        public String name;
    }
}
