package com.fuchsia.kyokushinkarate;

public class orangemodel {

    String Name,URL;

    orangemodel()
    {

    }

    public orangemodel(String Name, String URL) {
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
