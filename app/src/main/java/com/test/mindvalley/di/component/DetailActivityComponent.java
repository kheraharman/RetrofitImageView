package com.test.mindvalley.di.component;


import com.test.mindvalley.di.scopes.ActivityScope;
import com.test.mindvalley.ui.DetailActivity;

import dagger.Component;

@Component(dependencies = ApplicationComponent.class)
@ActivityScope
public interface DetailActivityComponent {

    void inject(DetailActivity detailActivity);
}
