package com.holmust.komal.myapplication.dashboard.RealmDatabase;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.Date;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by Komal on 9/30/2015.
 * Database class with methods that queries data from realm db
 */
public class DbUtil {

    final static String LOG_TAG = "Calorimeter-ShowFoodNut";
    static int dataSize = 0;

    /*
    Fetch number of food items that have been added into database
     */
    public static int getDataSize(Context context)
    {
        Realm realm = Realm.getInstance(context);
        realm.beginTransaction();
        RealmResults<FoodieDb> hallos = realm.where(FoodieDb.class).findAll();
        realm.commitTransaction();
        dataSize = hallos.size();
        return dataSize;
    }

    /*
    * This method returns total calorie intake for lunch, dinner, breakfast for today
    */
    public static float[] getCaloriesForToday(Context context)
    {
        float cal_lunch = 0;
        float cal_brk = 0;
        float cal_dinner = 0;
        float cal_snacks = 0;

        Realm realm = Realm.getInstance(context);

        Long start_time = new Date().getTime();
        realm.beginTransaction();
        RealmResults<FoodieDb> hallos = realm.where(FoodieDb.class).findAll();
        Long end_time = new Date().getTime();
        realm.commitTransaction();
        Long data_retireval_time = end_time-start_time; //Calculate data retrieval time from Realm db

        Log.d(LOG_TAG, "Data retrieval time in : :"+data_retireval_time +"ms");

        dataSize = hallos.size();

        for (FoodieDb object : hallos) {
            if(object.getMeal_type().equals("LUNCH"))
                cal_lunch = cal_lunch + object.getCalories();
            else if(object.getMeal_type().equals("BREAKFAST"))
                cal_brk = cal_brk + object.getCalories();
            else if(object.getMeal_type().equals("DINNER"))
                cal_dinner = cal_dinner + object.getCalories();
            else if(object.getMeal_type().equals("SNACKS"))
                cal_snacks = cal_snacks + object.getCalories();
            Log.i(LOG_TAG, object.getPrim_key());
        }
        float [] calories = {cal_brk,cal_lunch,cal_snacks,cal_dinner};
        /*calories.add(cal_brk);
        calories.add(cal_lunch);
        calories.add(cal_snacks);
        calories.add(cal_dinner);*/

        Log.i(LOG_TAG, " "+calories[3]);
        return calories;

    }

}
