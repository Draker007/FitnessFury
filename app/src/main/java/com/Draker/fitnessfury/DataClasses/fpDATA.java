package com.Draker.fitnessfury.DataClasses;

import android.graphics.drawable.Drawable;

public class fpDATA {
    int Image;
    String Name;

    public fpDATA(int image, String name) {
        Image = image;
        Name = name;
    }

    public int getImage() {
        return Image;
    }

    public void setImage(int image) {
        Image = image;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
