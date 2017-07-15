package com.burhan.rashid.daggermvprx.data.module;


import com.burhan.rashid.daggermvprx.contracter.HomeScreenContract;
import com.burhan.rashid.daggermvprx.contracter.MainScreenContract;
import com.burhan.rashid.daggermvprx.data.AppScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Burhanuddin Rashid on 11-May-16.
 */
@Module
public class BaseFragmentScreenModule {

    private HomeScreenContract.View mHomeView;

    public BaseFragmentScreenModule(HomeScreenContract.View mHomeView) {
        this.mHomeView = mHomeView;
    }

    @Provides
    @AppScope
    HomeScreenContract.View providesHomeScreenContractView() {
        return mHomeView;
    }
}
