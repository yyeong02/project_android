package com.example.teamprojectandroid;

public class CalItem {
    String medicine;
    String startdate;
    String finishdate;

    public CalItem(String medicine, String startdate, String finishdate){
        this.medicine = medicine;
        this.startdate = startdate;
        this.finishdate = finishdate;
    }

    public String getMedicine() {
        return medicine;
    }

    public String getStartdate() {
        return startdate;
    }

    public String getFinishdate() {
        return finishdate;
    }
}
