package com.dalvu.www.dalvyou.bean;

/**
 * 财务合同申请页数据的bean类
 * Created by user on 2017/6/30.
 */

public class ContractAskforDataBean {
    /**
     * status : 00000
     * msg : 成功
     * contractFeeInfo : {"contract_fee":"300","express_fee":"1000","address":"北京市朝阳区朝外神路街39号3-81","tel":"010-85626326"}
     * sign_token : 7b511c71e41f03a98011603abd4d01e2
     */

    public String status;
    public String msg;
    public ContractFeeInfoBean contractFeeInfo;
    public String sign_token;

    public static class ContractFeeInfoBean {
        /**
         * contract_fee : 300
         * express_fee : 1000
         * address : 北京市朝阳区朝外神路街39号3-81
         * tel : 010-85626326
         */

        public String contract_fee;
        public String express_fee;
        public String address;
        public String tel;
    }
}
