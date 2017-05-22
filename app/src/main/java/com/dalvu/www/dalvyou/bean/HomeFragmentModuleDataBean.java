package com.dalvu.www.dalvyou.bean;

import java.util.List;

/**首页数据的base类
 * Created by user on 2017/5/18.
 */

public class HomeFragmentModuleDataBean {

    /**
     * columnList : [{"name":"国内游","sort":1,"url":"/index.php/Api/Inbound/index.html"},
     * {"name":"出境游","sort":2,"url":"/index.php/Api/Outbound/index.html"},
     * {"name":"周边游","sort":3,"url":"/index.php/Api/Around/index.html"},
     * {"name":"自由行","sort":4,"url":"/index.php/Api/Freetravel/index.html"},
     * {"name":"保险/签证","sort":5,"url":"/index.php/Api/Visa/InsuranceVisa.html"},
     * {"name":"机票","sort":6,"url":"/index.php/Api/WxJsAPI/index.html"},
     * {"name":"门票","sort":7,"url":"/index.php/Api/Ticket/index.html"},
     * {"name":"特价","sort":8,"url":"/index.php/Api/BargainPrice/index.html"}]
     * msg : 成功
     * status : 00000
     */

    public String msg;
    public String status;
    public List<ColumnListBean> columnList;

    public static class ColumnListBean {
        /**
         * name : 国内游
         * sort : 1
         * url : /index.php/Api/Inbound/index.html
         */

        public String name;
        public int sort;
        public String url;
    }
}
