package com.example.completenilin.Retrofit;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;

import com.example.completenilin.Adapters.UsersAdapter;
import com.example.completenilin.Data.User;
import com.example.completenilin.Managers.UsersManager;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UsersNetworkClient implements Callback<ArrayList<User>> {
    static final String BASE_URL = "https://jsonplaceholder.typicode.com/";
    private static final String TAG = "usersNetwork";
    Context context;
    UsersAdapter adapter;
    private ProgressDialog progressDialog;


    public UsersNetworkClient(Context context, UsersAdapter adapter) {
        this.context = context;
        this.adapter = adapter;
    }

    public void start() {

        progressDialog = new ProgressDialog(context);
        showProgressDialog();

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        UsersAPI usersAPI = retrofit.create(UsersAPI.class);

        Call<ArrayList<User>> call = usersAPI.getUsers();
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<ArrayList<User>> call, Response<ArrayList<User>> response) {
        progressDialog.dismiss();
        if (response.isSuccessful()) {
            ArrayList<User> usersList = response.body();
            UsersManager usersManager = UsersManager.getInstance(context);
            usersManager.addUsers(usersList);
            adapter.notifyDataSetChanged();
        } else {
        }
    }

    @Override
    public void onFailure(Call<ArrayList<User>> call, Throwable t) {
        progressDialog.dismiss();
        showAlertDialog();
        t.printStackTrace();
    }

    private void showProgressDialog() {
        progressDialog.setMessage("Downloading albums...");
        progressDialog.setIndeterminate(true);
        progressDialog.show();
    }

    private void showAlertDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("ERROR !!");
        builder.setMessage("Sorry there was an error getting data from the Internet.");
        builder.setCancelable(false)
                .setPositiveButton("Retry", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int n) {
                        dialog.dismiss();
                        start();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }
}
