package com.holmust.komal.myapplication.dashboard.RealmDatabase;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Komal on 9/29/2015.
 */
public class FoodieDb extends RealmObject {


    @PrimaryKey private String prim_key; //<food_name>-<meal_type>-<date>-<serving_portion>-<serving_size>
    private String food_name,  meal_type;
    private Date date;
    private String serving_portion;
    private float serving_size;
    private float total_fat, sat_fat,
            calories, mono_fat, sodium, potassium, dietary_fiber, cholestrol,trans_fat, protein, total_carbs;

    public String getPrim_key() {
        return prim_key;
    }

    public void setPrim_key(String prim_key) {
        this.prim_key = prim_key;
    }

    public String getFood_name() {
        return food_name;
    }

    public void setFood_name(String food_name) {
        this.food_name = food_name;
    }

    public String getMeal_type() {
        return meal_type;
    }

    public void setMeal_type(String meal_type) {
        this.meal_type = meal_type;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getServing_portion() {
        return serving_portion;
    }

    public void setServing_portion(String serving_portion) {
        this.serving_portion = serving_portion;
    }

    public float getServing_size() {
        return serving_size;
    }

    public void setServing_size(float serving_size) {
        this.serving_size = serving_size;
    }

    public float getTotal_fat() {
        return total_fat;
    }

    public void setTotal_fat(float total_fat) {
        this.total_fat = total_fat;
    }

    public float getSat_fat() {
        return sat_fat;
    }

    public void setSat_fat(float sat_fat) {
        this.sat_fat = sat_fat;
    }

    public float getMono_fat() {
        return mono_fat;
    }

    public void setMono_fat(float mono_fat) {
        this.mono_fat = mono_fat;
    }

    public float getSodium() {
        return sodium;
    }

    public void setSodium(float sodium) {
        this.sodium = sodium;
    }

    public float getPotassium() {
        return potassium;
    }

    public void setPotassium(float potassium) {
        this.potassium = potassium;
    }

    public float getDietary_fiber() {
        return dietary_fiber;
    }

    public void setDietary_fiber(float dietary_fiber) {
        this.dietary_fiber = dietary_fiber;
    }

    public float getCholestrol() {
        return cholestrol;
    }

    public void setCholestrol(float cholestrol) {
        this.cholestrol = cholestrol;
    }

    public float getTrans_fat() {
        return trans_fat;
    }

    public void setTrans_fat(float trans_fat) {
        this.trans_fat = trans_fat;
    }

    public float getProtein() {
        return protein;
    }

    public void setProtein(float protein) {
        this.protein = protein;
    }

    public float getTotal_carbs() {
        return total_carbs;
    }

    public void setTotal_carbs(float total_carbs) {
        this.total_carbs = total_carbs;
    }

    public float getCalories() {
        return calories;
    }

    public void setCalories(float calories) {
        this.calories = calories;
    }
}
