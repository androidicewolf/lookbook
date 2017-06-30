package com.dalvu.www.dalvyou.bean;

/**
 * 充值申请页数据的bean
 * Created by user on 2017/6/29.
 */

public class RechargeAskforDataBean {
    /**
     * status : 00000
     * msg : 成功
     * operatorInfo : {"topupInfo":"开户名：北京大旅科技有限公司<br />账号：0200 0629 1902 4537456<br />开户行：工商银行北京日坛路支行<br /><br />开户名：北京大旅科技有限公司<br />账号：1100 6019 4018 8000 05920<br />开户行：交通银行北京东单支行<br /><br />开户名：康泰国际旅游（北京）有限公司<br />账号：0200 0041 1902 4518 965<br />开户行：工商银行东城支行"}
     * sign_token : c05965cc6446a00a6e57c2e3a696393a
     */

    public String status;
    public String msg;
    public OperatorInfoBean operatorInfo;
    public String sign_token;

    public static class OperatorInfoBean {
        /**
         * topupInfo : 开户名：北京大旅科技有限公司<br />账号：0200 0629 1902 4537456<br />开户行：工商银行北京日坛路支行<br /><br />开户名：北京大旅科技有限公司<br />账号：1100 6019 4018 8000 05920<br />开户行：交通银行北京东单支行<br /><br />开户名：康泰国际旅游（北京）有限公司<br />账号：0200 0041 1902 4518 965<br />开户行：工商银行东城支行
         */

        public String topupInfo;
    }
}
