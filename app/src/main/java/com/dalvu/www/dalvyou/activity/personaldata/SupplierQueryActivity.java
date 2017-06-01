package com.dalvu.www.dalvyou.activity.personaldata;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dalvu.www.dalvyou.R;
import com.dalvu.www.dalvyou.adapter.SupplierQueryAdapter;
import com.dalvu.www.dalvyou.base.BaseNoTitleActivity;
import com.dalvu.www.dalvyou.netUtils.MyCallBack;
import com.dalvu.www.dalvyou.netUtils.NetUtils;
import com.dalvu.www.dalvyou.netUtils.StateView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SupplierQueryActivity extends BaseNoTitleActivity {

    @BindView(R.id.iv_go_back)
    ImageView ivGoBack;
    @BindView(R.id.supplier_query_input_et)
    EditText supplierQueryInputEt;
    @BindView(R.id.supplier_startsearch)
    TextView supplierStartsearch;
    @BindView(R.id.supplier_query_newsupplier_tv)
    TextView supplierQueryNewsupplierTv;
    @BindView(R.id.supplier_query_stateview)
    StateView supplierQueryStateview;
    private String uri = "";
    private MyCallBack callBack;
    private XRecyclerView supplier_query_xrecycler;
    private ArrayList itemList = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supplier_query);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    private void initView() {
        supplierQueryStateview.addNormal(R.layout.supplier_xrecyclerview);
        supplier_query_xrecycler = (XRecyclerView) supplierQueryStateview.findViewById(R.id.supplier_query_xrecycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        supplier_query_xrecycler.setLayoutManager(layoutManager);
    }

    private void initData() {
        if (callBack == null) {
            callBack = new MyCallBack(supplierQueryStateview) {

                private SupplierQueryAdapter supplierQueryAdapter;

                @Override
                public void onStart(int what) {
                    if (supplierQueryStateview.state_Load.getVisibility() == View.GONE) {
                        supplierQueryStateview.showLoading();
                    }
                }

                @Override
                public void onSucceed(int what, String json) {
                    switch (what) {
                        case 0:

                            break;
                        case 1:

                            break;
                    }
                    if (supplierQueryAdapter == null) {
                        supplierQueryAdapter = new SupplierQueryAdapter(SupplierQueryActivity.this, itemList);
                        supplier_query_xrecycler.setAdapter(supplierQueryAdapter);
                    } else {
                        supplierQueryAdapter.notifyDataSetChanged();
                    }
                }
                @Override
                public void onFailed(int what, int code) {
                    super.onFailed(what, code);
                }
            };
        }
        NetUtils.callNet(0, uri, callBack);
    }

    @OnClick({R.id.iv_go_back, R.id.supplier_startsearch})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_go_back:
                finish();
                break;
            case R.id.supplier_startsearch:
                String supplierName = supplierQueryInputEt.getText().toString();
                if (supplierName.isEmpty()) {
                    Toast.makeText(this, "请输入目的地或者供应商名称", Toast.LENGTH_SHORT).show();
                } else {
                    supplierQueryNewsupplierTv.setText("查询结果");
                    //请求服务器,要携带参数

                    NetUtils.callNet(1, uri, callBack);
                }
                break;
        }
    }
}
