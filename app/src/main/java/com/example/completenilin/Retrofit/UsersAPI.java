package com.example.completenilin.Retrofit;

import com.example.completenilin.Data.User;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface UsersAPI {

    @GET("/users")
    Call<ArrayList<User>> getUsers();
}
