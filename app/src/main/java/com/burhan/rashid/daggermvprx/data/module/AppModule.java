package com.burhan.rashid.daggermvprx.data.module;

import android.app.Application;

import com.burhan.rashid.daggermvprx.data.NetworkScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Burhanuddin Rashid on 21-Apr-16.
 */
@Module
public class AppModule {
    Application mApplication;

    public AppModule(Application mApplication) {
        this.mApplication = mApplication;
    }

    @Provides
    @NetworkScope
    Application provideApplication() {
        return mApplication;
    }
}
