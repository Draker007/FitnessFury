package com.Draker.fitnessfury.DataClasses;

public class excersice {
    String excersiceImage;
    String excersiceName;
    String id;

    public excersice(String excersiceImage, String excersiceName, String id) {
        this.excersiceImage = excersiceImage;
        this.excersiceName = excersiceName;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }



    public String getExcersiceImage() {
        return excersiceImage;
    }

    public void setExcersiceImage(String excersiceImage) {
        this.excersiceImage = excersiceImage;
    }

    public String getExcersiceName() {
        return excersiceName;
    }

    public void setExcersiceName(String excersiceName) {
        this.excersiceName = excersiceName;
    }
}
