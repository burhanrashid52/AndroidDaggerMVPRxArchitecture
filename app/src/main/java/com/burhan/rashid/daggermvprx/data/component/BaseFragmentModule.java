package com.burhan.rashid.daggermvprx.data.component;

import com.burhan.rashid.daggermvprx.contracter.HomeScreenContract;
import com.burhan.rashid.daggermvprx.presenters.HomeScreenPresenter;

import dagger.Binds;
import dagger.Module;

@Module
abstract class BaseFragmentModule {

    @Binds
    abstract HomeScreenContract.Presenter provideHomePresenter(HomeScreenPresenter homeScreenPresenter);
}
