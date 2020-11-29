package com.airticket.client.db;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.widget.CursorAdapter;

import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;

public class FlightLoader implements LoaderManager.LoaderCallbacks<Cursor> {

    private Context mContext;
    private CursorAdapter mAdapter;

    public FlightLoader(Context context, CursorAdapter adapter) {
        this.mAdapter = adapter;
        this.mContext = context;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        Uri uri = Uri.parse(FlightProvider.FLIGHT_URI);
        return new CursorLoader(mContext, uri, null, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mAdapter.swapCursor(null);
    }
}
