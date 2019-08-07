package com.test.mindvalley.di.component;

import android.content.Context;


import com.test.mindvalley.di.module.AdapterModule;
import com.test.mindvalley.di.qualifier.ActivityContext;
import com.test.mindvalley.di.scopes.ActivityScope;
import com.test.mindvalley.ui.MainActivity;

import dagger.Component;


@ActivityScope
@Component(modules = AdapterModule.class, dependencies = ApplicationComponent.class)
public interface MainActivityComponent {

    @ActivityContext
    Context getContext();


    void injectMainActivity(MainActivity mainActivity);
}
