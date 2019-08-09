package com.burhan.rashid.daggermvprx.data.component;

import android.app.Application;

import com.burhan.rashid.daggermvprx.data.NetworkScope;
import com.burhan.rashid.daggermvprx.data.module.NetModule;
import com.burhan.rashid.daggermvprx.networks.ApiService;

import dagger.BindsInstance;
import dagger.Component;

/**
 * Created by Burhanuddin Rashid on 21-Apr-16.
 */
@NetworkScope
@Component(modules = NetModule.class)
public interface NetComponent {

    ApiService getApiService();

    Application getApplication();

    @Component.Builder
    interface Builder {
        NetComponent build();

        Builder netModule(NetModule netModule);

        @BindsInstance
        Builder application(Application application);
    }
}
