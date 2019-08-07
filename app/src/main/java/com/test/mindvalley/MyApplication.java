package com.test.mindvalley;

import android.app.Activity;
import android.app.Application;

import com.test.mindvalley.di.component.ApplicationComponent;
import com.test.mindvalley.di.component.DaggerApplicationComponent;
import com.test.mindvalley.di.module.ContextModule;


public class MyApplication extends Application {

    ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        applicationComponent = DaggerApplicationComponent.builder().contextModule(new ContextModule(this)).build();
        applicationComponent.injectApplication(this);

    }

    public static MyApplication get(Activity activity){
        return (MyApplication) activity.getApplication();
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}

