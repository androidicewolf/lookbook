package com.dalvu.www.dalvyou.bean;

import java.util.List;

/**
 * 首页线路列表的数据
 * Created by user on 2017/6/22.
 */

public class HomeFragmentLineDataBean {
    /**
     * list : [{"cover_pic":"http://file1.lydlr.com/c/240/160/tour/2015/0620/01800323621.jpg","departure":"北京 北京","id":"479","min_price":"396000","name":"北京直飞平壤、开城板门店、三八线、妙香山双飞4日游","tour_sku_count":"1"},{"cover_pic":"http://file1.lydlr.com/c/240/160/tour/2017/0408/00299643076.jpg","departure":"北京 北京","id":"4304","min_price":"1439900","name":"莫斯科+圣彼得堡+金环+庄园+贝加尔湖+喀琅施塔得+新西伯利亚欧亚全景11日行程","tour_sku_count":"2"},{"cover_pic":"http://file1.lydlr.com/c/240/160/tour/2015/0620/01651231468.jpg","departure":"北京 北京","id":"482","min_price":"495000","name":"北京直飞平壤、开城板门店、妙香山、朝鲜美术创作社、电影城基地双飞6日游","tour_sku_count":"1"},{"cover_pic":"http://file1.lydlr.com/c/240/160/tour/2016/0722/01414731830.jpg","departure":"北京 北京","id":"4293","min_price":"845000","name":"PEKWAW","tour_sku_count":"1"},{"cover_pic":"http://file1.lydlr.com/c/240/160/tour/2017/0414/01173481906.jpg","departure":"北京 北京","id":"4378","min_price":"1700000","name":"西欧U6 盈尚 一价全含 德法意瑞14天","tour_sku_count":"1"},{"cover_pic":"http://file1.lydlr.com/c/240/160/tour/2017/0408/01811433492.jpg","departure":"北京 北京","id":"4305","min_price":"949900","name":"浪漫俄罗斯欧亚全景8日+金环行程","tour_sku_count":"1"},{"cover_pic":"http://file1.lydlr.com/c/240/160/tour/2017/0406/00865702021.jpg","departure":"北京 北京","id":"4299","min_price":"1329900","name":"莫斯科+圣彼得堡+喀琅施塔得+伊尔库茨克+贝加尔湖+新西伯利亚9日行程","tour_sku_count":"2"},{"cover_pic":"http://file1.lydlr.com/c/240/160/tour/2017/0219/00294893189.jpg","departure":"北京 北京","id":"4104","min_price":"428000","name":"特色游-北京火车丹东、平壤、开城、三八线、东林、新义州双卧7日游","tour_sku_count":"2"},{"cover_pic":"http://file1.lydlr.com/c/240/160/tour/2015/0620/00891901001.jpg","departure":"北京 北京","id":"480","min_price":"405000","name":"北京直飞平壤、开城板门店、三八线、妙香山、南浦双飞5日游","tour_sku_count":"1"},{"cover_pic":"http://file1.lydlr.com/c/240/160/tour/2015/0620/01824391569.jpg","departure":"北京 北京","id":"483","min_price":"558000","name":"北京直飞平壤、开城板门店、元山、金刚山双飞6日游","tour_sku_count":"1"},{"cover_pic":"http://file1.lydlr.com/c/240/160/tour/2015/0621/01125517390.jpg","departure":"北京 北京","id":"485","min_price":"358000","name":"朝鲜D2：北京火车丹东、平壤、开城板门店、三八线、妙香山、南浦双卧7日游","tour_sku_count":"1"},{"cover_pic":"http://file1.lydlr.com/c/240/160/tour/2015/0621/01519867789.jpg","departure":"北京 北京","id":"487","min_price":"408000","name":"朝鲜D4：北京火车平壤、开城、妙香山、朝鲜美术创作社、电影城基地全景双卧8日","tour_sku_count":"2"},{"cover_pic":"http://file1.lydlr.com/c/240/160/tour/2015/0621/01301238129.jpg","departure":"北京 北京","id":"488","min_price":"468000","name":"深度游-北京火车丹东、平壤、开城、三八线、元山、金刚山深度双卧8日游","tour_sku_count":"2"},{"cover_pic":"http://file1.lydlr.com/c/240/160/tour/2016/0531/00231640027.jpg","departure":"北京 北京","id":"1658","min_price":"839900","name":"斯里兰卡6晚8天 茶园·雅拉随团游","tour_sku_count":"1"}]
     * msg : 成功
     * promotionList : []
     * sign_token : 515291a49e64b246f3365d4ea021ff19
     * status : 00000
     */

    public String msg;
    public String sign_token;
    public String status;
    public List<ListBean> list;
    public List<String> promotionList;

    public static class ListBean {
        /**
         * cover_pic : http://file1.lydlr.com/c/240/160/tour/2015/0620/01800323621.jpg
         * departure : 北京 北京
         * id : 479
         * min_price : 396000
         * name : 北京直飞平壤、开城板门店、三八线、妙香山双飞4日游
         * tour_sku_count : 1
         */

        public String cover_pic;
        public String departure;
        public String id;
        public String min_price;
        public String name;
        public String tour_sku_count;
    }
}
