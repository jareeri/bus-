package com.example.bus;

import android.app.Application;

import com.example.bus.model.Driver;
import com.example.bus.model.User;


public class UserClient extends Application {

    public static User getUser = null;
    public static Driver getDriver = null;

    private User user = null;
    private Driver driver = null;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
        getUser = user;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
        getDriver = driver;
    }
}
