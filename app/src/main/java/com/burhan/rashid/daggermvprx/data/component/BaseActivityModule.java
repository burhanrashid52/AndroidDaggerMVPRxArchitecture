package com.burhan.rashid.daggermvprx.data.component;

import com.burhan.rashid.daggermvprx.contracter.MainScreenContract;
import com.burhan.rashid.daggermvprx.presenters.MainScreenPresenter;

import dagger.Binds;
import dagger.Module;

@Module
abstract class BaseActivityModule {

    @Binds
    public abstract MainScreenContract.Presenter provideMainPresenterContract(MainScreenPresenter mainScreenPresenter);
}
