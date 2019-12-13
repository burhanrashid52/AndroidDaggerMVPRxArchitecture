package com.burhan.rashid.daggermvprx.data.module;

import android.app.Application;

import com.burhan.rashid.daggermvprx.App;
import com.burhan.rashid.daggermvprx.data.NetworkScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Burhanuddin Rashid on 21-Apr-16.
 */
@Module
public class AppModule {

    @Provides
    @NetworkScope
    Application provideApplication(App app) {
        return app;
    }
}
