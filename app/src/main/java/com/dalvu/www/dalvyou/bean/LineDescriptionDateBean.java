package com.dalvu.www.dalvyou.bean;

/**
 * 线路详情页的产品亮点数据的bean类
 * Created by user on 2017/6/27.
 */

public class LineDescriptionDateBean {
    /**
     * status : 00000
     * msg : 成功
     * list : {"description":"<p>含京津往返大巴<br/><\/p><p>自费推荐不强迫<\/p><p>天津直飞 拒绝转机<\/p>"}
     */

    public String status;
    public String msg;
    public ListBean list;

    public static class ListBean {
        /**
         * description : <p>含京津往返大巴<br/></p><p>自费推荐不强迫</p><p>天津直飞 拒绝转机</p>
         */

        public String description;
    }
}
