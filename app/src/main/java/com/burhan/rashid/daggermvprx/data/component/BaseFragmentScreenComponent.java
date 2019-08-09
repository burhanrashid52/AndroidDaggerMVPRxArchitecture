package com.burhan.rashid.daggermvprx.data.component;

import com.burhan.rashid.daggermvprx.contracter.HomeScreenContract;
import com.burhan.rashid.daggermvprx.data.AppScope;
import com.burhan.rashid.daggermvprx.fragments.HomeFragment;

import dagger.BindsInstance;
import dagger.Component;

/**
 * Created by Burhanuddin Rashid on 11-May-16.
 */
@AppScope
@Component(dependencies = NetComponent.class)
public interface BaseFragmentScreenComponent {

    void inject(HomeFragment homeFragment);

    @Component.Builder
    interface Builder {

        BaseFragmentScreenComponent build();

        Builder netComponent(NetComponent netComponent);

        @BindsInstance
        Builder createHomeView(HomeScreenContract.View homeView);
    }
}