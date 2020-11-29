package com.airticket.client.ui;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.airticket.client.R;
import com.airticket.client.db.DBHelper;
import com.airticket.client.utils.StringUtil;

/**
 * class desc :
 *
 * @author :
 * @date : 2019/3/6
 */
public class ForgetPasswordActivity extends BaseActivity {

    private EditText etAccount;
    private TextView tvResult;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        etAccount = findViewById(R.id.etAccount);
        tvResult = findViewById(R.id.tvResult);

        setToolBar("忘记密码", true);

        findViewById(R.id.submitLayout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findPassword();
            }
        });
    }

    private void findPassword() {
        String account = etAccount.getText().toString().trim();
        if (TextUtils.isEmpty(account)) {
            Toast.makeText(this, "请输入手机号", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!StringUtil.isPhoneNum(account)) {
            Toast.makeText(this, "请输入正确的手机号", Toast.LENGTH_SHORT).show();
            return;
        }
        String password = DBHelper.userFindPassword(account);
        if (TextUtils.isEmpty(password)) {
            tvResult.setText("该帐号没有注册");
        } else {
            tvResult.setText(password);
        }
    }
}
