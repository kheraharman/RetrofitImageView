package com.test.mindvalley.ui;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;


import com.facebook.shimmer.ShimmerFrameLayout;
import com.test.mindvalley.MyApplication;
import com.test.mindvalley.R;
import com.test.mindvalley.adapter.RecyclerViewAdapter;
import com.test.mindvalley.di.component.ApplicationComponent;
import com.test.mindvalley.di.component.DaggerMainActivityComponent;
import com.test.mindvalley.di.component.MainActivityComponent;
import com.test.mindvalley.di.module.MainActivityContextModule;
import com.test.mindvalley.di.qualifier.ActivityContext;
import com.test.mindvalley.di.qualifier.ApplicationContext;
import com.test.mindvalley.pojo.Example;
import com.test.mindvalley.retrofit.APIInterface;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements RecyclerViewAdapter.ClickListener {

    private RecyclerView recyclerView;
    MainActivityComponent mainActivityComponent;

    private static String TAG="MINDVALLEY";
    @Inject
    public RecyclerViewAdapter recyclerViewAdapter;

    @Inject
    public APIInterface apiInterface;

    @Inject
    @ApplicationContext
    public Context mContext;

    @Inject
    @ActivityContext
    public Context activityContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        ApplicationComponent applicationComponent = MyApplication.get(this).getApplicationComponent();
        mainActivityComponent = DaggerMainActivityComponent.builder()
                .mainActivityContextModule(new MainActivityContextModule(this))
                .applicationComponent(applicationComponent)
                .build();

        mainActivityComponent.injectMainActivity(this);
        recyclerView.setAdapter(recyclerViewAdapter);

        isWriteStoragePermissionGranted();
    }


    private void CallService()
    {
        apiInterface.getData("json").enqueue(new Callback<List<Example>>() {
            @Override
            public void onResponse(Call<List<Example>> call, Response<List<Example>> response) {
                Log.d("Response",response.body().toString());
                populateRecyclerView(response.body());
            }

            @Override
            public void onFailure(Call<List<Example>> call, Throwable t) {

            }
        });
    }
    private void populateRecyclerView(List<Example> response) {
        recyclerViewAdapter.setData(response);
    }


    @Override
    public void launchIntent(String url) {
        Toast.makeText(mContext, "RecyclerView Row selected", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(activityContext, DetailActivity.class).putExtra("url", url));
    }

    public  boolean isWriteStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
               // Log.v(TAG,"Permission is granted2");
                CallService();
                return true;
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 2);
                return false;
            }
        }
        else { //permission is automatically granted on sdk<23 upon installation
            return true;
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 2:
                if(grantResults[0]== PackageManager.PERMISSION_GRANTED){
                    Log.v(TAG,"Permission: "+permissions[0]+ "was "+grantResults[0]);
                    CallService();
                }else{

                }
                break;
        }
    }
}

