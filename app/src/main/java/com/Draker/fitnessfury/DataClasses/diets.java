package com.Draker.fitnessfury.DataClasses;

public class diets {
    String name , image , meals , duration;

    public diets(String name, String image, String meals, String duration) {
        this.name = name;
        this.image = image;
        this.meals = meals;
        this.duration = duration;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getMeals() {
        return meals;
    }

    public void setMeals(String meals) {
        this.meals = meals;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
}
