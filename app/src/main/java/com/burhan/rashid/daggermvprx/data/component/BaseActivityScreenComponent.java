package com.burhan.rashid.daggermvprx.data.component;

import com.burhan.rashid.daggermvprx.data.AppScope;
import com.burhan.rashid.daggermvprx.activities.MainActivity;
import com.burhan.rashid.daggermvprx.data.module.BaseActivityScreenModule;

import dagger.Component;

/**
 * Created by Burhanuddin Rashid on 11-May-16.
 */
@AppScope
@Component(dependencies = NetComponent.class, modules = BaseActivityScreenModule.class)
public interface BaseActivityScreenComponent {
    void inject(MainActivity mainActivity);
}
