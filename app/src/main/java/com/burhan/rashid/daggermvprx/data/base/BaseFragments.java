package com.burhan.rashid.daggermvprx.data.base;

import android.app.ProgressDialog;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;

import com.burhan.rashid.daggermvprx.R;
import com.burhan.rashid.daggermvprx.data.base.BasePresenter;

/**
 * Created by Burhanuddin on 7/1/2017.
 */

public abstract class BaseFragments extends Fragment {

    private ProgressDialog progressDialog;

    public abstract BasePresenter getPresenter();

    public void showLoading() {
        showLoading(null);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (getPresenter() != null) {
            getPresenter().resume();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (getPresenter() != null) {
            getPresenter().pause();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (getPresenter() != null) {
            getPresenter().destroy();
        }
    }

    public void showLoading(String msg) {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(getActivity());
        }
        progressDialog.setCancelable(false);
        String message = msg != null ? msg : getResources().getString(R.string.txt_please_wait);
        progressDialog.setMessage(message);
        progressDialog.show();
    }

    public void hideLoading() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    public void showErrorMessage(String message) {
        showSnackbar(true, message);
    }

    private void showSnackbar(boolean isError, String message) {
        Snackbar make = Snackbar.make(getView(), message, Snackbar.LENGTH_LONG);
        if (isError) {
            make.getView().setBackgroundColor(getResources().getColor(android.R.color.holo_red_dark));
        }
        make.show();
    }

    public void showMessage(String message) {
        showSnackbar(false, message);
    }
}
