package com.example.insulinpump;

public class Value {
    public String tdd,basal,bolus;
    public Value()
    {

    }
    public Value(String tdd,String basal,String bolus)
    {
        this.tdd=tdd;
        this.basal=basal;
        this.bolus=bolus;

    }

    public String getBolus() {
        return bolus;
    }

    public String getBasal() {
        return basal;
    }

    public String getTdd() {
        return tdd;
    }
}
