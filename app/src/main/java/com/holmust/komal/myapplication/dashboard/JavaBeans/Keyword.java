package com.holmust.komal.myapplication.dashboard.JavaBeans;

import java.io.Serializable;

/**
 * Created by Komal on 9/25/2015.
 */
public class Keyword implements Serializable{

    private String tag;
    private String score;

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
}
