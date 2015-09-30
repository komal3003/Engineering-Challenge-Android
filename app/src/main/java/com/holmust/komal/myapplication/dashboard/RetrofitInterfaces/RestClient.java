package com.holmust.komal.myapplication.dashboard.RetrofitInterfaces;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


/**
 * Created by Komal on 9/25/2015.
 */
public class RestClient {

    private static ApiService REST_CLIENT;
    private static String ROOT =
            "https://test.holmusk.com/food";

    static {
        setupRestClient();
    }

    private RestClient() {}

    public static ApiService get() {
        return REST_CLIENT;
    }

    private static void setupRestClient() {
        Gson gson = new GsonBuilder().create();


        /*.Builder builder = new Retrofit.Builder()
                .baseUrl(ROOT)
                .addConverterFactory(GsonConverterFactory.create(gson));


        Retrofit retrofit = builder.build();
        REST_CLIENT = retrofit.create(ApiService.class);*/
    }
}
