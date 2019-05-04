package com.example.completenilin.Managers;

import android.content.Context;

import com.example.completenilin.Data.User;

import java.util.ArrayList;

public class UsersManager {

    private static UsersManager instance;
    private ArrayList<User> users = new ArrayList<>();

    private UsersManager(Context context) {

    }

    public static UsersManager getInstance(Context context) {
        if (instance == null)
            instance = new UsersManager(context);
        return instance;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void addUsers(ArrayList<User> usersList) {
        users.addAll(usersList);
    }
}
