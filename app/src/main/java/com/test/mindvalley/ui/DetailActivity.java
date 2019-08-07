package com.test.mindvalley.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;


import com.test.mindvalley.MyApplication;
import com.test.mindvalley.R;
import com.test.mindvalley.di.component.ApplicationComponent;
import com.test.mindvalley.di.component.DaggerDetailActivityComponent;
import com.test.mindvalley.di.component.DetailActivityComponent;
import com.test.mindvalley.di.qualifier.ApplicationContext;
import com.test.mindvalley.retrofit.APIInterface;

import javax.inject.Inject;

public class DetailActivity extends AppCompatActivity {

    DetailActivityComponent detailActivityComponent;

    @Inject
    public APIInterface apiInterface;

    @Inject
    @ApplicationContext
    public Context mContext;

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        textView = findViewById(R.id.textView);

        String url = getIntent().getStringExtra("url");

        ApplicationComponent applicationComponent = MyApplication.get(this).getApplicationComponent();
        detailActivityComponent = DaggerDetailActivityComponent.builder()
                .applicationComponent(applicationComponent)
                .build();

        detailActivityComponent.inject(this);


    }
}
