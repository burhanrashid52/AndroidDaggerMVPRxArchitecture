package com.burhan.rashid.daggermvprx.data.component;

import android.app.Application;

import com.burhan.rashid.daggermvprx.data.NetworkScope;
import com.burhan.rashid.daggermvprx.data.module.AppModule;
import com.burhan.rashid.daggermvprx.data.module.NetModule;
import com.burhan.rashid.daggermvprx.networks.ApiService;

import dagger.Component;

/**
 * Created by Burhanuddin Rashid on 21-Apr-16.
 */
@NetworkScope
@Component(modules = {AppModule.class, NetModule.class})
public interface NetComponent {
    // downstream components need these exposed with the return type
    // method name does not really matter
    ApiService getApiService();

    Application getApplication();
}
