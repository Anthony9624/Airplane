package com.airticket.client.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.airticket.client.ActivityController;
import com.airticket.client.Constans;
import com.airticket.client.R;
import com.airticket.client.utils.SPUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GuestActivity extends BaseActivity implements AdapterView.OnItemClickListener {

    private SimpleAdapter mAdapter;
    private String[] mLabels = new String[]{"航班查询", "已定机票", "机票退款", "信息查询"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest);
        setToolBar("机票管理", true);

        GridView mGvMenu = (GridView) findViewById(R.id.id_gv_menu);
        TextView mTvUser = (TextView) findViewById(R.id.id_tv_username);
        initData();
        mTvUser.setText("Hi ~ " + Constans.USER_NAME);
        mGvMenu.setAdapter(mAdapter);
        mGvMenu.setOnItemClickListener(this);

        findViewById(R.id.btn_logout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLogoutDialog();
            }
        });
    }

    private void showLogoutDialog() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setMessage("确认退出登录吗？");
        alertDialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alertDialog.setPositiveButton("退出登录", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                SPUtil.putString(GuestActivity.this, Constans.SP.USER_TOKEN, "");
                ActivityController.finishAll();
                startActivity(new Intent(GuestActivity.this, SplashActivity.class));
                dialog.dismiss();
            }
        });
        alertDialog.show();
    }

    private void initData() {
        String[] from = {"text"};
        int[] to = {R.id.id_item_menu_label};
        List<Map<String, Object>> datas = new ArrayList<>();
        for (int i = 0; i < mLabels.length; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("text", mLabels[i]);
            datas.add(map);
        }
        mAdapter = new SimpleAdapter(this, datas, R.layout.item_menu, from, to);
    }


    /**
     * 菜单单击时
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:
                switchToActivity(SearchActivity.class);
                break;
            case 1:
                switchToActivity(OrdersActivity.class);
                break;
            case 2:
                switchToActivity(RefundActivity.class);
                break;
            case 3:
                switchToActivity(FlightInfoActivity.class);
                break;
        }
    }

    /**
     * 切换到指定的Activity
     */
    private void switchToActivity(Class activity) {
        Intent intent = new Intent(this, activity);
        intent.putExtra("user_name", Constans.USER_NAME);
        startActivity(intent);
    }
}
