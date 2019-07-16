package com.Draker.fitnessfury.DataClasses;

public class weekNo {
    String week , status;

    public weekNo(String week, String status) {
        this.week = week;
        this.status = status;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
