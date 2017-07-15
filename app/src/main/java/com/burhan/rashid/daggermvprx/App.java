package com.burhan.rashid.daggermvprx;

import android.app.Application;

import com.burhan.rashid.daggermvprx.data.component.DaggerNetComponent;
import com.burhan.rashid.daggermvprx.data.component.NetComponent;
import com.burhan.rashid.daggermvprx.data.module.AppModule;
import com.burhan.rashid.daggermvprx.util.PrefUtils;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by Burhanuddin Rashid on 11-May-16.
 */
public class App extends Application {
    private static App instance;
    private NetComponent mNetComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        //Initialize Preference Util
        PrefUtils.init(this);
        //Initialize Realm local database
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(config);
        //Initialize Dagger Net Component
        //Here
        mNetComponent = DaggerNetComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public NetComponent getNetComponent() {
        return mNetComponent;
    }

    public static App getInstance() {
        return instance;
    }
}
