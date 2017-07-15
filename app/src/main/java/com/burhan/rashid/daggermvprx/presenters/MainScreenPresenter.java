package com.burhan.rashid.daggermvprx.presenters;

import android.app.Application;

import com.burhan.rashid.daggermvprx.contracter.MainScreenContract;
import com.burhan.rashid.daggermvprx.networks.ApiService;

import javax.inject.Inject;

/**
 * Created by Burhanuddin Rashid on 11-May-16.
 */
public class MainScreenPresenter implements MainScreenContract.Presenter {
    MainScreenContract.View mView;
    private Application application;
    private ApiService apiService;

    @Inject
    public MainScreenPresenter(Application application, ApiService apiService, MainScreenContract.View mView) {
        this.application = application;
        this.apiService = apiService;
        this.mView = mView;
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {

    }
}
