package com.holmust.komal.myapplication.dashboard.JavaBeans;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import io.realm.RealmObject;

/**
 * Created by Komal on 9/27/2015.
 */
public class Foods {

    @SerializedName(value="foods")
    private List<FoodValue> foods;

    public void setFoods(List<FoodValue> foods) {
        this.foods = foods;

    }

    public List<FoodValue> getFoods() {
        return foods;
    }
}
