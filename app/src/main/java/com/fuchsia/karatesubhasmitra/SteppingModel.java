package com.fuchsia.karatesubhasmitra;


public class SteppingModel {

    String Name,URL;

    SteppingModel()
    {

    }

    public SteppingModel(String Name, String URL) {
        this.Name = Name;
        this.URL = URL;

    }


    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

}

