package com.airticket.client.ui;

import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.airticket.client.R;

/**
 * class desc :
 *
 * @author :
 */
abstract class BaseFragment extends Fragment {

    /**
     * 设置toolbar的标题并居中显示
     *
     * @param title 标题
     */
    public void setToolBar(String title) {
        Toolbar toolbar = (Toolbar) getView().findViewById(R.id.mToolbar);
        toolbar.setTitle(title);
        setTitleCenter(toolbar);
    }

    /**
     * 设置toolbar标题居中显示
     *
     * @param toolbar Toolbar
     */
    private void setTitleCenter(Toolbar toolbar) {
        String title = "title";
        final CharSequence originalTitle = toolbar.getTitle();
        toolbar.setTitle(title);
        for (int i = 0; i < toolbar.getChildCount(); i++) {
            View view = toolbar.getChildAt(i);
            if (view instanceof TextView) {
                TextView textView = (TextView) view;
                if (title.equals(textView.getText())) {
                    textView.setGravity(Gravity.CENTER);
                    Toolbar.LayoutParams params = new Toolbar.LayoutParams(Toolbar.LayoutParams.WRAP_CONTENT, Toolbar.LayoutParams.MATCH_PARENT);
                    params.gravity = Gravity.CENTER;
                    textView.setLayoutParams(params);
                }
            }
            toolbar.setTitle(originalTitle);
        }
    }
}
