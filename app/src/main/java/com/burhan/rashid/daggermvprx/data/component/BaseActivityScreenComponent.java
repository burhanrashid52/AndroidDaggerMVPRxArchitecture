package com.burhan.rashid.daggermvprx.data.component;

import com.burhan.rashid.daggermvprx.activities.MainActivity;
import com.burhan.rashid.daggermvprx.contracter.MainScreenContract;
import com.burhan.rashid.daggermvprx.data.AppScope;

import dagger.BindsInstance;
import dagger.Component;

/**
 * Created by Burhanuddin Rashid on 11-May-16.
 */
@AppScope
@Component(dependencies = NetComponent.class, modules = BaseActivityModule.class)
public interface BaseActivityScreenComponent {

    void inject(MainActivity mainActivity);

    @Component.Builder
    interface Builder {

        BaseActivityScreenComponent build();

        Builder netComponent(NetComponent netComponent);

        @BindsInstance
        Builder mainView(MainScreenContract.View mainView);
    }
}
