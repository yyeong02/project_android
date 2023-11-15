package com.example.teamprojectandroid;

public class CalItem {
    String medicine, startdate, finishdate, detail1, detail2, detail3, detail4, detail5, memo;

    public CalItem(String medicine, String startdate, String finishdate, String detail1, String detail2, String detail3, String detail4, String detail5, String memo) {
        this.medicine = medicine;
        this.startdate = startdate;
        this.finishdate = finishdate;
        this.detail1 = detail1;
        this.detail2 = detail2;
        this.detail3 = detail3;
        this.detail4 = detail4;
        this.detail5 = detail5;
        this.memo = memo;
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

    public String getDetail1() {
        return detail1;
    }

    public String getDetail2() {
        return detail2;
    }

    public String getDetail3() {
        return detail3;
    }

    public String getDetail4() {
        return detail4;
    }

    public String getDetail5() {
        return detail5;
    }

    public String getMemo() {
        return memo;
    }
}
