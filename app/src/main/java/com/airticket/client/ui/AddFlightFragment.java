package com.airticket.client.ui;

import android.content.ContentValues;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.airticket.client.R;
import com.airticket.client.db.FlightDAO;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;

/**
 * 添加航班信息
 */
public class AddFlightFragment extends Fragment implements View.OnClickListener {

    private View mRoot;
    private TextInputLayout mTilCompany;
    private TextInputLayout mTilStarting;
    private TextInputLayout mTilEnding;
    private TextInputLayout mTilTime;
    private TextInputLayout mTilPrice;
    private Button mBtnSubmit;
    private FlightDAO mFlightDAO;

    public AddFlightFragment() {
    }

    @Override
    public void onResume() {
        super.onResume();
        mFlightDAO = new FlightDAO();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRoot = inflater.inflate(R.layout.fragment_add_flight, container, false);
        initView();
        return mRoot;
    }

    private void initView() {
        mTilCompany = (TextInputLayout) mRoot.findViewById(R.id.id_til_add_company);
        mTilStarting = (TextInputLayout) mRoot.findViewById(R.id.id_til_add_starting);
        mTilEnding = (TextInputLayout) mRoot.findViewById(R.id.id_til_add_ending);
        mTilTime = (TextInputLayout) mRoot.findViewById(R.id.id_til_add_time);
        mTilPrice = (TextInputLayout) mRoot.findViewById(R.id.id_til_add_price);
        mBtnSubmit = (Button) mRoot.findViewById(R.id.id_btn_flight_submit);
        mBtnSubmit.setOnClickListener(this);
        mTilCompany.setHint("航空公司");
        mTilStarting.setHint("起点");
        mTilEnding.setHint("终点");
        mTilTime.setHint("时间");
        mTilPrice.setHint("价格");
    }


    /**
     * 向数据库插入数据
     */
    @Override
    public void onClick(View v) {
        // 五个字段均非空
        String company = mTilCompany.getEditText().getText().toString();
        String starting = mTilStarting.getEditText().getText().toString();
        String ending = mTilEnding.getEditText().getText().toString();
        String price = mTilPrice.getEditText().getText().toString();
        String flightTime = mTilTime.getEditText().getText().toString();
        if (!TextUtils.isEmpty(company)
                && !TextUtils.isEmpty(starting)
                && !TextUtils.isEmpty(ending)
                && !TextUtils.isEmpty(price)
                && !TextUtils.isEmpty(flightTime)) {
            ContentValues values = new ContentValues();
            values.put("flight_company", company);
            values.put("flight_starting", starting);
            values.put("flight_ending", ending);
            values.put("flight_price", price);
            values.put("flight_time", flightTime);
            int row = mFlightDAO.insertFlight(values);
            if (row > 0) {
                Snackbar.make(mRoot, "添加成功", Snackbar.LENGTH_SHORT).show();
                getLoaderManager().restartLoader(AddTicketFragment.LOADER_ID, null, AddTicketFragment.mLoader);
            }
        } else {
            Snackbar.make(mRoot, "输入错误，请重新输入", Snackbar.LENGTH_SHORT).show();
        }
    }
}
