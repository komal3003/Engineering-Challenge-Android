package com.holmust.komal.myapplication.dashboard.JavaBeans;

import java.io.Serializable;

/**
 * Created by Komal on 9/29/2015.
 */
public class Content implements Serializable{
    private String unit;
    private double value;
    private String name;


    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
