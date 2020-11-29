package com.airticket.client.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.airticket.client.Constans;
import com.airticket.client.R;
import com.airticket.client.db.DBHelper;
import com.airticket.client.utils.SPUtil;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_user).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String token = SPUtil.getString(MainActivity.this, Constans.SP.USER_TOKEN, "");
                if (TextUtils.isEmpty(token)) {
                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                } else {
                    DBHelper.userFind(token);
                    startActivity(new Intent(MainActivity.this, GuestActivity.class));
                }
            }
        });

        findViewById(R.id.btn_admin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FlightActivity.class);
                startActivity(intent);
            }
        });
    }
}
