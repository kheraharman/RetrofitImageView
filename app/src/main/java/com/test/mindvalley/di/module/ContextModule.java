package com.test.mindvalley.di.module;

import android.content.Context;


import com.test.mindvalley.di.qualifier.ApplicationContext;
import com.test.mindvalley.di.scopes.ApplicationScope;

import dagger.Module;
import dagger.Provides;

@Module
public class ContextModule {
    private Context context;

    public ContextModule(Context context) {
        this.context = context;
    }

    @Provides
    @ApplicationScope
    @ApplicationContext
    public Context provideContext() {
        return context;
    }
}
