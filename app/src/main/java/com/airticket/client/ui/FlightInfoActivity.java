package com.airticket.client.ui;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.CursorAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.airticket.client.R;
import com.airticket.client.db.FlightDAO;

public class FlightInfoActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight_info);
        setToolBar("航班信息",true);
        ListView mLvFlights = (ListView) findViewById(R.id.id_lv_flights_info);
        FlightDAO mFlightDAO = new FlightDAO();
        Cursor mCursor = mFlightDAO.rawQuery("select * from flight_capacity", null);
        ListAdapter mAdapter = new FlightInfoAdapter(this, mCursor
                , CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        mLvFlights.setAdapter(mAdapter);
    }
}
