package com.example.insulinpump;

public class User {
    public String name,email,password,tdd,basal,bolus,battery,doctorcode;
    public User()
    {

    }
    public User(String namee,String emaill,String passwordd,String doctorcode,String tdd,String basal, String bolus,String battery)
    {
        this.name=namee;
        this.email=emaill;
        this.password=passwordd;
        this.doctorcode = doctorcode;
        this.tdd = tdd;
        this.basal = basal;

        this.bolus = bolus;
        this.battery=battery;

    }

   public String getEmail() {
        return email;
    }



    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }




    public String getTdd() {
        return tdd;
    }

    public String getBasal() {
        return basal;
    }

    public String getBattery() {
        return battery;
    }

    public String getBolus() {
        return bolus;
    }
}
