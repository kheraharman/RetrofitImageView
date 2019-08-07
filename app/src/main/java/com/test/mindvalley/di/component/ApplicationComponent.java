package com.test.mindvalley.di.component;

import android.content.Context;


import com.test.mindvalley.MyApplication;
import com.test.mindvalley.di.module.ContextModule;
import com.test.mindvalley.di.module.RetrofitModule;
import com.test.mindvalley.di.qualifier.ApplicationContext;
import com.test.mindvalley.di.scopes.ApplicationScope;
import com.test.mindvalley.retrofit.APIInterface;

import dagger.Component;

@ApplicationScope
@Component(modules = {ContextModule.class, RetrofitModule.class})
public interface ApplicationComponent {

    public APIInterface getApiInterface();

    @ApplicationContext
    public Context getContext();

    public void injectApplication(MyApplication myApplication);
}
