package com.dalvu.www.dalvyou.bean;

/**
 * 财务提现申请页数据的bean类
 * Created by user on 2017/6/29.
 */

public class GetCashAskforDataBean {
    /**
     * status : 00000
     * msg : 成功
     * agencyInfo : {"id":"295","account_balance":"70911349","bank_account":"2323232321232321232","bank_name":"工商银行","account_state":"1","availableBalance":"64989349"}
     * sign_token : a12570ddcb419955cc882f4c6d6b797a
     */

    public String status;
    public String msg;
    public AgencyInfoBean agencyInfo;
    public String sign_token;

    public static class AgencyInfoBean {
        /**
         * id : 295
         * account_balance : 70911349
         * bank_account : 2323232321232321232
         * bank_name : 工商银行
         * account_state : 1
         * availableBalance : 64989349
         */

        public String id;
        public String account_balance;
        public String bank_account;
        public String bank_name;
        public String account_state;
        public String availableBalance;
    }
}
