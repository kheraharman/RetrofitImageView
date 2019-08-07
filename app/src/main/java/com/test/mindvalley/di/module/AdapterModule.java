package com.test.mindvalley.di.module;


import com.test.mindvalley.adapter.RecyclerViewAdapter;
import com.test.mindvalley.di.scopes.ActivityScope;
import com.test.mindvalley.ui.MainActivity;

import dagger.Module;
import dagger.Provides;

@Module(includes = {MainActivityContextModule.class})
public class AdapterModule {

    @Provides
    @ActivityScope
    public RecyclerViewAdapter getStarWarsPeopleLIst(RecyclerViewAdapter.ClickListener clickListener) {
        return new RecyclerViewAdapter(clickListener);
    }

    @Provides
    @ActivityScope
    public RecyclerViewAdapter.ClickListener getClickListener(MainActivity mainActivity) {
        return mainActivity;
    }
}
