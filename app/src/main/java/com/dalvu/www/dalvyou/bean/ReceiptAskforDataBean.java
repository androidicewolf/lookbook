package com.dalvu.www.dalvyou.bean;

import java.util.List;

/**
 * 财务发票申请页数据的bean类
 * Created by user on 2017/6/29.
 */

public class ReceiptAskforDataBean {
    /**
     * status : 00000
     * msg : 成功
     * lastTotal : -15957900
     * operatorInfo : {"express_fee":"1000","address":"北京市朝阳区朝外神路街39号3-81","tel":"010-85626326"}
     * invoiceTypeList : [{"id":"1","operator_id":"1","detail":"团款","fee_rate":"5000","create_time":"2016-10-11 10:30:31","remark":null,"update_time":null,"is_delete":"0"},{"id":"2","operator_id":"1","detail":"旅游费","fee_rate":"5000","create_time":"2016-10-11 10:30:31","remark":null,"update_time":null,"is_delete":"0"}]
     * sign_token : 0c770d10ba8ca7736af47ee72ea2d31c
     */

    public String status;
    public String msg;
    public String lastTotal;
    public OperatorInfoBean operatorInfo;
    public String sign_token;
    public List<InvoiceTypeListBean> invoiceTypeList;

    public static class OperatorInfoBean {
        /**
         * express_fee : 1000
         * address : 北京市朝阳区朝外神路街39号3-81
         * tel : 010-85626326
         */

        public String express_fee;
        public String address;
        public String tel;
    }

    public static class InvoiceTypeListBean {
        /**
         * id : 1
         * operator_id : 1
         * detail : 团款
         * fee_rate : 5000
         * create_time : 2016-10-11 10:30:31
         * remark : null
         * update_time : null
         * is_delete : 0
         */

        public String id;
        public String operator_id;
        public String detail;
        public String fee_rate;
        public String create_time;
        public String remark;
        public String update_time;
        public String is_delete;
    }
}
