package com.holmust.komal.myapplication.dashboard.JavaBeans;

import java.io.Serializable;

import io.realm.RealmObject;

/**
 * Created by Komal on 9/25/2015.
 */
public class Nutrient implements Serializable{

    private ImportantNutrients important;

    public ImportantNutrients getImportant() {
        return important;
    }

    public void setImportant(ImportantNutrients important) {
        this.important = important;
    }

    /*enum type {

        IMPORTANT,
        EXTRA
    };*/

}
