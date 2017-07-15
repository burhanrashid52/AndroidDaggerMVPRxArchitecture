package com.burhan.rashid.daggermvprx.data.component;

import com.burhan.rashid.daggermvprx.data.AppScope;
import com.burhan.rashid.daggermvprx.data.module.BaseActivityScreenModule;
import com.burhan.rashid.daggermvprx.data.base.BaseFragments;
import com.burhan.rashid.daggermvprx.data.module.BaseFragmentScreenModule;
import com.burhan.rashid.daggermvprx.fragments.HomeFragment;

import dagger.Component;

/**
 * Created by Burhanuddin Rashid on 11-May-16.
 */
@AppScope
@Component(dependencies = NetComponent.class, modules = BaseFragmentScreenModule.class)
public interface BaseFragmentScreenComponent {
    void inject(HomeFragment homeFragment);
}