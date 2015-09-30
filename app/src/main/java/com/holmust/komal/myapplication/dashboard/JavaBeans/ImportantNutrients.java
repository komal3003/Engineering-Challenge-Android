package com.holmust.komal.myapplication.dashboard.JavaBeans;

import java.io.Serializable;
import java.util.HashMap;

import io.realm.RealmObject;

/**
 * Created by Komal on 9/28/2015.
 */
public class ImportantNutrients implements Serializable{

    private Content dietary_fibre = new Content();
    private Content sodium = new Content();
    private Content potassium = new Content();
    private Content total_carbs = new Content();
    private Content calories = new Content();
    private Content sugar = new Content();
    private Content polyunsaturated = new Content();
    private Content monounsaturated = new Content();
    private Content cholestrol = new Content();
    private Content protein = new Content();
    private Content trans = new Content();
    private Content saturated = new Content();
   private Content total_fats = new Content();

    public Content getDietary_fibre() {
        return dietary_fibre;
    }

    public void setDietary_fibre(Content dietary_fibre) {
        this.dietary_fibre = dietary_fibre;
    }

    public Content getSodium() {
        return sodium;
    }

    public void setSodium(Content sodium) {
        this.sodium = sodium;
    }

    public Content getPotassium() {
        return potassium;
    }

    public void setPotassium(Content potassium) {
        this.potassium = potassium;
    }

    public Content getTotal_carbs() {
        return total_carbs;
    }

    public void setTotal_carbs(Content total_carbs) {
        this.total_carbs = total_carbs;
    }

    public Content getCalories() {
        return calories;
    }

    public void setCalories(Content calories) {
        this.calories = calories;
    }

    public Content getSugar() {
        return sugar;
    }

    public void setSugar(Content sugar) {
        this.sugar = sugar;
    }

    public Content getPolyunsaturated() {
        return polyunsaturated;
    }

    public void setPolyunsaturated(Content polyunsaturated) {
        this.polyunsaturated = polyunsaturated;
    }

    public Content getMonounsaturated() {
        return monounsaturated;
    }

    public void setMonounsaturated(Content monounsaturated) {
        this.monounsaturated = monounsaturated;
    }

    public Content getCholestrol() {
        return cholestrol;
    }

    public void setCholestrol(Content cholestrol) {
        this.cholestrol = cholestrol;
    }

    public Content getProtein() {
        return protein;
    }

    public void setProtein(Content protein) {
        this.protein = protein;
    }

    public Content getTrans() {
        return trans;
    }

    public void setTrans(Content trans) {
        this.trans = trans;
    }

    public Content getSaturated() {
        return saturated;
    }

    public void setSaturated(Content saturated) {
        this.saturated = saturated;
    }

    public Content getTotal_fats() {
        return total_fats;
    }

    public void setTotal_fats(Content total_fats) {
        this.total_fats = total_fats;
    }
}
