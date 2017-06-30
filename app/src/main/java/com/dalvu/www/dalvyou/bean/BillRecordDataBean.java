package com.dalvu.www.dalvyou.bean;

import java.util.List;

/**
 * 财务各种申请记录数据的bean类
 * Created by user on 2017/6/28.
 */

public class BillRecordDataBean {
    /**
     * list : [{"action":"支出线路产品订单金额","amount":"-2679800","balance":"70911349","create_time":"2017-06-28 09:22:12","id":"8372","sn":"14986129325008"},{"action":"支出线路产品订单金额","amount":"-5359600","balance":"73591149","create_time":"2017-06-28 09:21:56","id":"8367","sn":"14986129162485"},{"action":"支出线路产品订单金额","amount":"-1000000","balance":"78950749","create_time":"2017-06-28 09:21:44","id":"8362","sn":"14986129046981"},{"action":"支出线路产品订单金额","amount":"-1339900","balance":"79950749","create_time":"2017-06-27 19:57:31","id":"8357","sn":"14985646511938"},{"action":"支出线路产品订单金额","amount":"-2679800","balance":"81290649","create_time":"2017-06-27 15:49:10","id":"8352","sn":"14985497506863"},{"action":"支出线路产品订单金额","amount":"-1384900","balance":"83970449","create_time":"2017-06-27 15:48:53","id":"8347","sn":"14985497335396"},{"action":"支出线路产品订单金额","amount":"-1339900","balance":"85355349","create_time":"2017-06-27 13:59:54","id":"8342","sn":"14985431946315"},{"action":"支出线路产品订单金额","amount":"-1339900","balance":"86695249","create_time":"2017-06-27 13:48:48","id":"8337","sn":"14985425287726"},{"action":"支出线路产品订单金额","amount":"-1350000","balance":"88035149","create_time":"2017-06-27 12:20:42","id":"8332","sn":"14985372423594"},{"action":"支出线路产品订单金额","amount":"-1300000","balance":"89385149","create_time":"2017-06-27 12:13:51","id":"8327","sn":"14985368315654"}]
     * msg : 成功
     * sign_token : 89d710d75ee6a0ef5b2bbf95912491e3
     * status : 00000
     */

    public String msg;
    public String sign_token;
    public String status;
    public List<ListBean> list;

    public static class ListBean {
        /**
         * action : 支出线路产品订单金额
         * amount : -2679800
         * balance : 70911349
         * create_time : 2017-06-28 09:22:12
         * id : 8372
         * sn : 14986129325008
         */

        public String action;
        public String amount;
        public String balance;
        public String create_time;
        public String id;
        public String sn;
        public String state;
        public String memo;
        public String payer;
        public String commission_rate;
        public String account;
        public String actualPrice;
        public String title;
        public String detail;
        public String detail_comm;
        public String inland_count;
        public String outbound_count;
        public String peritem_count;
    }
}
