package com.dalvu.www.dalvyou.bean;

/**
 * 财务页数据的bean类
 * Created by user on 2017/6/28.
 */

public class BillFragmentDataBean {

    /**
     * status : 00000
     * msg : 成功
     * agencyInfo : {"id":"295","account_balance":"70911349","freezeMoney":"5910900","availableBalance":"65000449"}
     * sign_token : 36d9bff735482bbb72e39560de2c8e7b
     */

    public String status;
    public String msg;
    public AgencyInfoBean agencyInfo;
    public String sign_token;

    public static class AgencyInfoBean {
        /**
         * id : 295
         * account_balance : 70911349
         * freezeMoney : 5910900
         * availableBalance : 65000449
         */

        public String id;
        public String account_balance;
        public String freezeMoney;
        public String availableBalance;
    }
}
