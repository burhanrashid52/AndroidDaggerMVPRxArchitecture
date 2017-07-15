package com.burhan.rashid.daggermvprx.data.base;

import android.app.ProgressDialog;
import android.support.design.widget.Snackbar;

import com.burhan.rashid.daggermvprx.R;

/**
 * Created by Burhanuddin on 7/1/2017.
 */

public interface BaseView {
    void showLoading();

    void showLoading(String msg);

    void hideLoading();

    void showErrorMessage(String message);

    void showMessage(String message);
}
