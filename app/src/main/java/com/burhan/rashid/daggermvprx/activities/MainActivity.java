package com.burhan.rashid.daggermvprx.activities;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.burhan.rashid.daggermvprx.App;
import com.burhan.rashid.daggermvprx.R;
import com.burhan.rashid.daggermvprx.contracter.MainScreenContract;
import com.burhan.rashid.daggermvprx.data.base.BaseActivity;
import com.burhan.rashid.daggermvprx.data.component.DaggerBaseActivityScreenComponent;
import com.burhan.rashid.daggermvprx.fragments.HomeFragment;
import com.burhan.rashid.daggermvprx.util.ActivityUtils;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements MainScreenContract.View {

    @Inject
    MainScreenContract.Presenter mainPresenter;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        DaggerBaseActivityScreenComponent.builder()
                .netComponent(App.getInstance().getNetComponent())
                .mainView(this)
                .build()
                .inject(this);
        //Add Home Fragment to activity
        ActivityUtils.replaceFragmentToActivity(getSupportFragmentManager(), HomeFragment.newInstance(), R.id.frmContainer, HomeFragment.TAG);
    }

    @Override
    public int getToolbar() {
        return R.id.toolbar;
    }

    @Override
    public boolean hasBackNavigation() {
        return false;
    }

    @Override
    public String getToolbarTitle() {
        return "Architecture Example";
    }
}
