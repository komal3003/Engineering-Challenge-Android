package com.holmust.komal.myapplication.dashboard.JavaBeans;

import android.os.Parcelable;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Komal on 9/25/2015.
 */
public class FoodValue implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private String _id;

    private String brand_name, source, length;
    private ArrayList<Portion> portions = new ArrayList<Portion>();
    private ArrayList<Keyword> keywords = new ArrayList<Keyword>();


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getBrand_name() {
        return brand_name;
    }

    public void setBrand_name(String brand_name) {
        this.brand_name = brand_name;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public ArrayList<Portion> getPortions() {
        return portions;
    }

    public void setPortions(ArrayList<Portion> portions) {
        this.portions = portions;
    }

    public ArrayList<Keyword> getKeywords() {
        return keywords;
    }

    public void setKeywords(ArrayList<Keyword> keywords) {
        this.keywords = keywords;
    }
}
