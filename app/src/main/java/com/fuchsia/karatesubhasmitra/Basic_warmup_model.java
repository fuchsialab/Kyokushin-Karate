package com.fuchsia.karatesubhasmitra;

public class Basic_warmup_model {

    String Name,URL;

    Basic_warmup_model()
    {

    }

    public Basic_warmup_model(String Name, String URL) {
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
