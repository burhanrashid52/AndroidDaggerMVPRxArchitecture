package com.burhan.rashid.daggermvprx.data.base;

import android.app.ProgressDialog;
import android.support.annotation.LayoutRes;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.burhan.rashid.daggermvprx.R;

/**
 * Created by Burhanuddin on 7/1/2017.
 */

public abstract class BaseActivity extends AppCompatActivity {

    public abstract int getToolbar();

    public abstract boolean hasBackNavigation();

    public abstract String getToolbarTitle();

    private ProgressDialog progressDialog;

    public void showLoading() {
        showLoading(null);
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        if (getToolbar() != 0) {
            Toolbar toolbar = (Toolbar) findViewById(getToolbar());
            setSupportActionBar(toolbar);
            if (hasBackNavigation()) {
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                getSupportActionBar().setDisplayShowHomeEnabled(true);
            }
            if (getToolbarTitle() != null) {
                getSupportActionBar().setTitle(getToolbarTitle());
            }
        }
    }

    public void showLoading(String msg) {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(this);
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
        View view = findViewById(android.R.id.content);
        if (view != null) {
            Snackbar make = Snackbar.make(view, message, Snackbar.LENGTH_LONG);
            if (isError) {
                make.getView().setBackgroundColor(getResources().getColor(android.R.color.holo_red_dark));
            }
            make.show();
        }
    }

    public void showMessage(String message) {
        showSnackbar(false, message);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            // close this activity and return to preview activity (if there is any)
            onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }
}
