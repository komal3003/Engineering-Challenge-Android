package com.holmust.komal.myapplication.dashboard.JavaBeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Komal on 9/25/2015.
 */
public class Portion implements Serializable{

    private String name;

    private Nutrient nutrients = new Nutrient();
    private Nutrient extra = new Nutrient();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /*public ImportantNutrients getImportant() {
        return important;
    }

    public void setImportant(ImportantNutrients important) {
        this.important = important;
    }*/

    public Nutrient getExtra() {
        return extra;
    }

    public void setExtra(Nutrient extra) {
        this.extra = extra;
    }

    public Nutrient getNutrients() {
        return nutrients;
    }

    public void setNutrients(Nutrient nutrients) {
        this.nutrients = nutrients;
    }
}
