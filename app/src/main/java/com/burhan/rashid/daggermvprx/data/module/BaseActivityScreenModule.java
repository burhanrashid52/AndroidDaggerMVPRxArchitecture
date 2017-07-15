package com.burhan.rashid.daggermvprx.data.module;


import com.burhan.rashid.daggermvprx.contracter.HomeScreenContract;
import com.burhan.rashid.daggermvprx.data.AppScope;
import com.burhan.rashid.daggermvprx.contracter.MainScreenContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Burhanuddin Rashid on 11-May-16.
 */
@Module
public class BaseActivityScreenModule {

    private MainScreenContract.View mView;

    public BaseActivityScreenModule(MainScreenContract.View mView) {
        this.mView = mView;
    }

    @Provides
    @AppScope
    MainScreenContract.View providesMainScreenContractView() {
        return mView;
    }
}
