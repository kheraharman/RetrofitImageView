package com.test.mindvalley.retrofit;



import com.test.mindvalley.pojo.Example;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIInterface {

    @GET(".")
    Call<List<Example>> getData(@Query("format") String format);

}
