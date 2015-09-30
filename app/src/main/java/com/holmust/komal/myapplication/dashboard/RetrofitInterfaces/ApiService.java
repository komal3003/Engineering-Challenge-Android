package com.holmust.komal.myapplication.dashboard.RetrofitInterfaces;

import com.holmust.komal.myapplication.dashboard.JavaBeans.FoodValue;
import com.holmust.komal.myapplication.dashboard.JavaBeans.Foods;


import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by Komal on 9/25/2015.
 */
public interface ApiService {

    @GET("/search")
    //Foods getFoodValue(@Query("q") String key
    void getFoodValue(@Query("q") String key, Callback<List<FoodValue>> cb);

}
